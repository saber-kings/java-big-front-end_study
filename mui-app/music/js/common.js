function resize() {
    let width = window.screen.width;
    const basicvalue = 320; //设计稿上的分辨率大小
    
    //放大100倍，为了方便计算rem大小
    document.documentElement.style.fontSize = (width / basicvalue) * 100 + 'px';
}

//加上以下代码是为了切换设备时能时刻刷新动态fontSize，从而得到正确的布局
window.addEventListener('resize', function () {
    resize();
});
window.addEventListener('DOMContentLoaded', function () {
   resize();
});