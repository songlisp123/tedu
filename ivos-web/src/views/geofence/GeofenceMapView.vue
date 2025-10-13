<template>
    <!-- 围栏地图页面 -->
    <div style="height: 6vh; background-color: #fff;padding: 10px 20px;">
        <span style="line-height: 60px; font-size: 20px;">蔚蓝地图</span>
        <el-button type="primary" @click="router.push('/geofence')" 
        style="float: right; margin-top: 13px;">返回</el-button>
    </div>

    <!-- 准备一个div充当地图容器 -->
     <div id="mapContanier" style="width: 100%;height: 84vh;" >

     </div>
     <el-dialog v-model="dialogVisible"
        title="新建围栏">
        <el-form>
            <el-form-item label="围栏名称">
                <el-input placeholder="请输入围栏名称" v-model="geofence.name"></el-input>
            </el-form-item>
        </el-form>
        <template #footer>
            <el-button @click="backup()">取消</el-button>
            <el-button type="primary" @click="submit()">确定</el-button>
        </template>
     </el-dialog>
</template>

<script setup>
import router from '@/router';
import { nextTick, onMounted, ref } from 'vue';
import { DrawScene, DrawControl , OperateEventType } from 'bmap-draw';
import axios from 'axios';
import { ElMessage } from 'element-plus';


//定义变量接受围栏状态
const geofence = ref({
    name:'', //围栏名称
    position:{} //围栏坐标
});

//定义变量显示弹窗
const dialogVisible = ref(false);

//定义全局变量用来保存地图对象
var map;


//页面已加载就调用此函数
onMounted(()=>{
    //确保dom对象完全加载完毕后调用此方法，因为我们确保地图容器完全加载
    nextTick(()=>{

        /****    绘制地图     * */
        //创建地图对象,注意:此处无需导入!
        map = new BMapGL.Map("mapContanier");
        //创建地图的展示中心点,此处的中心点是天安门的经纬度
        let point = new BMapGL.Point(116.4074, 39.9024);
        //设置地图对象的中心点与缩放级别
        map.centerAndZoom(point, 15);
        // map.enableScrollWheelZoom();
 
        var marker = new BMapGL.Marker(point);        // 创建标注   
        map.addOverlay(marker);                     // 将标注添加到地图中


        // 添加鼠标绘制控件
        const scene = new DrawScene(map);
        scene.enableCalculate = true;
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

        //监听用户操作
        scene.addEventListener(OperateEventType.COMPLETE,(e)=>{
            //10:用户一点击对钩，弹出弹窗
            dialogVisible.value = true;
            //target对象是封装的overLay的对象
            console.log(e.target);
            /* 获取围栏信息 ,overLay对象有一个属性overlay 封装了图像的数据*/
            let overlay = e.target.overlay;
            console.log(overlay);

            /**获取当前围栏的类型（圆形）还是方形 */
            if (overlay instanceof BMapGL.Circle) {
                //为围栏设置数据
                geofence.value.position.type = 'circle'; //获取类型数据
                geofence.value.position.longitude = overlay.latLng.lng; //获取覆盖物的进度
                geofence.value.position.latitude = overlay.latLng.lat; //覆盖物的维度
                geofence.value.position.radius = overlay.radius; //
                console.log(geofence.value);
            }else {
                geofence.value.position.type = 'rectangle';
                //定义一个数组用来存放当前经纬度
                let recPoints = [];
                //遍历矩形的当前坐标
                for (let i = 0;i<4;i++) {
                    // console.log(overlay.points[i].latLng.lng);
                    // console.log(overlay.points[i].latLng.lat);

                    recPoints.push(
                        overlay.points[i].latLng.lng+'-'+overlay.points[i].latLng.lat
                    );
                }
                
                //将数组中的元素以分隔符分割成字符串
                geofence.value.position.recPoints = recPoints.join(',');
                console.log(geofence.value);
            }
        });
        map.addControl(drawContrl);
    });
});

/**
 * 定义保存围栏的函数
 */
function submit() {

    if (!geofence.value.name || geofence.value.name.trim() == '' ) {
        ElMessage.error('错误');
        return;
    }
    dialogVisible.value = false;
    console.log(geofence.value.position);
    console.log(typeof(geofence.value.position));

    geofence.value.position = JSON.stringify(geofence.value.position);
    console.log(geofence.value.position);
    console.log(typeof(geofence.value.position));
    console.log(geofence.value);


    axios.post(BASE_URL+'/v1/geofence/save',geofence.value)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('新建围栏成功！');
            geofence.value = {name:'',position:{}};
            dialogVisible.value = false;
            router.go(0); //刷新当前界面
        }
        else {
            ElMessage.error('围栏创建失败,请稍后再试');
        }
});
}

/**
 * 定义关闭弹窗的方法
 */
function backup() {
    if (confirm('是否要取消此操作？')) {
        map.clearOverlays(); //清空当前地图对象
        dialogVisible.value = false; //关闭弹窗
        geofence.value = {name:'',position:{}};
    }
}
</script>

<style>

</style>