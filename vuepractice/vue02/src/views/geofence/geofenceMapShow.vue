<template>
    <div style="height: 6vh;background-color:rgb(12, 75, 51);padding:10px 10%;">
            <span style="line-height:60px;font-size:20px;">围栏地图</span>
            <el-button type="primary" style="float:right;margin-top:13px;"
            @click="router.go(-1)">返回</el-button>
    </div>
    <div id="map" style="width: 100%;height: 83vh;"></div>
</template>


<script setup>
import router from '@/router';
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { nextTick, onMounted } from 'vue';

//定义全局的地图变量
var map;

//定义变量接收当前围栏的数据
const geofence = ({
    name:'',//围栏名称
    position:{} //围栏数据
})

//获取当前围栏的id
const geoId = new URLSearchParams(window.location.search).get('id');

onMounted(()=>{
    nextTick(()=>{
        map = new BMapGL.Map('map');
        showGeofence();
    });
});


function showGeofence() {
    axios.get(BASE_URL+'/v1/geofence/query?id='+geoId)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('拿到数据！');
            //加载数据坐标点
            let data = JSON.parse(response.data.data[0].position);
            //定义一个遮盖物
            let overlay;
            if (data.type === 'circle') {
                let point = new BMapGL.Point(data.longitude,data.latitude);
                let marker = new BMapGL.Marker(point);
                console.log(point);
                overlay = new BMapGL.Circle(
                    point,
                    data.radius,
                    {strokeColor:'green',strokeWeight:2,strokeOpacity:1}
                );
                // console.log(overlay);
                //初始化地图，设置中心点
                map.centerAndZoom(point,15);
                map.addOverlay(overlay);
                map.addOverlay(marker);
                map.enableScrollWheelZoom();
            }
            else {
                let points = data.recPoints.split(',');
                let vertexes = [];
                let marker;
                for (let i = 0;i<points.length;i++) {
                    if (i == 0) {
                        let point = new BMapGL.Point(points[i].split('-')[0],points[i].split('-')[1]);
                        marker = new BMapGL.Marker(point);
                        map.centerAndZoom(point,15);
                    }
                    let point = new BMapGL.Point(points[i].split('-')[0],points[i].split('-')[1]);
                    vertexes.push(point);
                }
                overlay = new BMapGL.Polygon(
                    vertexes,
                    {strokeColor:'green',strokeWeight:2,strokeOpacity:1}
                )
                map.addOverlay(overlay);
                map.addOverlay(marker);
                map.enableScrollWheelZoom();
            }

        }
        else {
            ElMessage.error('查询失败!');
        }
    });
}
</script>

<style></style>