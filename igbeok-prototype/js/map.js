var courseLocations = [
    {
        "name": "独墅湖高教区文星广场",
        "longitude": "120.748151",
        "latitude": "31.282601",
        "address": "工业园区独墅湖高教区仁爱路199号文星广场"
    },
    {
        "name": "湖东邻里中心",
        "longitude": "120.735198",
        "latitude": "31.320121",
        "address": "苏州工业园区星湖街 178号"
    },
    {
        "name": "玲珑湾邻里中心",
        "longitude": "120.707265",
        "latitude": "31.336142",
        "address": "工业园区玲珑街58号"
    },
    {
        "name": "苏州市三香路体育中心",
        "longitude": "120.586416",
        "latitude": "31.302304",
        "address": "江苏省苏州市姑苏区三香路183号"
    }
];

function baiduMap() {
    var map = new BMap.Map("allmap");   // 创建Map实例
    var point = new BMap.Point(120.69472, 31.307626);    // 创建点坐标
    map.centerAndZoom(point,12);    // 初始化地图,设置中心点坐标和地图级别
    map.enableScrollWheelZoom(); 
    
    var courseLocations = getLocations();
    courseLocations.forEach(function(elem, index) {
        var marker = createMarker(index, elem);
        map.addOverlay(marker); 
    });
}

function createMarker(index, option) {
    var position = new BMap.Point(option.longitude, option.latitude);
    
    var icon = new BMap.Icon('../img/ico/marker.png', new BMap.Size(29,23));
    
    var markerOptions = {
        icon: icon,
        title: option.name
    };
    return new BMap.Marker(position, markerOptions);
}

function getLocations() {
    return courseLocations;
}

baiduMap();