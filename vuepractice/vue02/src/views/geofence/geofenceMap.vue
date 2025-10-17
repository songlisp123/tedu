<template>
    <el-dialog
    title="新建围栏"
    v-model="dialogVisible"
    style="width: 800px;padding: 20px; 
    flex-direction: column;
    align-items: center;
    display: flex; justify-content: center;"
    >
        <div style="margin: 5px 0;">
            <label for="name" style="color: #000; font-size: 1.2em; 
            display: inline-block;
            font-weight: var(--font-weight);">
                围栏名称：<span style="color: var(--invalid-color);">*</span></label> <br>
            <input type="text" id="name"
                placeholder="请输入名字" v-model="geofence.name"
                required

            >
            </div>
            <template #footer>
                <el-button type="info" @click="cancel()">取消</el-button>
                <el-button type="primary" @click="submit()">确定</el-button>
            </template>
    </el-dialog>
    <div style="height: 6vh;background-color:rgb(12, 75, 51);padding:10px 10%;">
            <span style="line-height:60px;font-size:20px;">围栏地图</span>
            <el-button type="primary" style="float:right;margin-top:13px;"
            @click="router.go(-1)">返回</el-button>
    </div>
    <div>
        <div id="mapContainer" style="width: 100%; height: 90vh;"></div>
    </div>
</template>

<script setup>
import router from '@/router';
import { nextTick, onMounted, ref } from 'vue';
import { DrawScene, DrawControl,OperateEventType} from 'bmap-draw';
import axios from 'axios';
import qs from 'qs';
import { ElMessage } from 'element-plus';

//定义变量保存围栏数据
const geofence = ref({
    name:'',
    position:{}
});

// 定义弹窗保存围栏选项
const dialogVisible = ref(false);


//定义全局变量
var map;


onMounted(()=>{
    nextTick(()=>{
        map = new BMapGL.Map('mapContainer');

        let point = new BMapGL.Point(116.04,39.915);
        map.centerAndZoom(point,15);
        map.enableScrollWheelZoom(true);
        // map.setHeading(60);
        // map.setTilt(65);

         // 添加鼠标绘制控件
        const scene = new DrawScene(map);
        const drawContrl = new DrawControl(scene, {
            enableTips: false,
            anchor: BMAP_ANCHOR_TOP_RIGHT,
            drawingItems: [
                'marker',
                'polyline',
                'polygon',
                'circle',
                'rectangle'
            ]
        });
        map.addControl(drawContrl);

        scene.addEventListener(OperateEventType.COMPLETE,(e)=>{
            dialogVisible.value = true;
            let overlay = e.target.overlay;
            if (overlay instanceof BMapGL.Circle) {

                geofence.value.position.type = '圆形';
                geofence.value.position.lat = overlay.getCenter().lat;
                geofence.value.position.lng = overlay.getCenter().lng;
                console.log(geofence.value);
            }
            
        });
    });
});

function submit() {
    console.log(geofence.value);
    geofence.value.position = JSON.stringify(geofence.value.position);    

    console.log(geofence.value);
    let data = qs.stringify(geofence.value);
    axios.post(BASE_URL+'/v1/geofence/save',data)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('新增成功！');
            geofence.value.name = '';
            geofence.value.position = {};
            dialogVisible.value = false;

        }
        else {
            ElMessage.error('新增失败，请稍后再试');    
        }
    });
    
}

function cancel() {
    dialogVisible.value = false;
    geofence.value.name = '';
    geofence.value.position = {};
    map.clearOverlays();
}

</script>



<style>
 .info {
        z-index: 999;
        width: auto;
        min-width: 22rem;
        padding: .75rem 1.25rem;
        margin-left: 10%;
        position: fixed;
        top: 15%;
        background-color: rgba(265, 265, 265, 0.9);
        border-radius: .25rem;
        font-size: 14px;
        color: #666;
        box-shadow: 0 2px 6px 0 rgba(27, 142, 236, 0.3);
 }

#zoominput {
    height: 24px;
    width: 80px;
    padding-left: 8px;
}
#change-btn {
    height: 30px;
    background: #5679ea;
    border: 0;
    padding: 0 10px 0 10px;
    margin: 10px 8px 0 0;
    cursor: pointer;
    border-radius: 2px;
    color: #fff;
    font-size: 14px;
}

.info input {
    color: #666;
}

#name{
    border: none;
    width: 200px;
    height: 30px;
    border-bottom: 1px solid #666;
    color: #666;
    background-color: rgb(244, 244, 244);
}

#name:valid{
    border-bottom-color: var(--valid-color);
}

#name:invalid{
    border-bottom-color: var(--invalid-color);
}


</style>