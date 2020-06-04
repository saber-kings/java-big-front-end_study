<template>
    <div class="">
        <TodayRecommend title="今日榜单"/>
        <NewSongs title="新歌速递"/>
        <SwiperBanner title="轮播"/>
        <MusicListNav title="歌曲列表导航"/>
        <router-view />
    </div>
</template>

<script>

    import TodayRecommend from "../components/Today_Recommend";
    import NewSongs from "../components/New_Songs";
    import SwiperBanner from "../components/Swiper_Banner";
    import MusicListNav from  "./musiclist/music_listnav";


    export default {
        name: "home",
        data() {
            return {}
        },
        components: {
            NewSongs,
            TodayRecommend,
            SwiperBanner,
            MusicListNav,

        },
        mounted() {
            // axios.get('v1/restserver/ting?from=webapp_music&format=json&method=baidu.ting.plaza.newEditionIndex&version=7.1.3'
            //     // params: {
            //     //     from: "webapp_music",
            //     //     format: "json",
            //     //     method: "baidu.ting.plaza.newEditionIndex",
            //     //     s_protocol: "1",
            //     // }
            // ).then((res) => {
            //     console.log(res);
            // }).catch((err) => {
            //     console.log(err)
            // })
        }
    }
</script>

<style scoped lang="scss">

</style>