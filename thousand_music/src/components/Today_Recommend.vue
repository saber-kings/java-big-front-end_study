<template lang="html">
    <div class="mod-albums">
        <div class="hd log url">
            <h2>今日推荐</h2>
            <div>更多</div>
        </div>
        <div class="container">
            <div class="gallery">
                <div class="scroller">
                    <div class="card url" v-for="(item,index) in todayRecommend" :key="index">
                        <div class="album">
                            <img :src="item.pic_big" :alt="item.title">
                            <div class="name">{{ item.title }}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name:"TodayRecommend",
        data() {
            return {
                todayRecommend:[]
            }
        },
        mounted(){
            axios.get('v1/restserver/ting?from=webapp_music&format=json&param=z4TYX3tDFoytKrK6OAQxJz0RtxkjXIdSHBXN%2F4I6djgUx5KmOJzHeOjipQrYn4eJf0b4w4cY45JdUp0gGymWFQ%3D%3D&timestamp=1575901121&sign=28a8521523e7cdae0a213e15d968371a&method=baidu.ting.ugcdiy.getBaseInfo&s_protocol=1'
                // params: {
                //     from: "webapp_music",
                //     format: "json",
                //     method: "baidu.ting.plaza.newEditionIndex",
                //     s_protocol: "1",
                // }
            ).then((res) => {
                // console.log(res)
                var songList=res.data.result.songlist;
                this.todayRecommend = songList.slice(0,6)
            }).catch((err) => {
                console.log(err)
            })
        }
    }
</script>

<style scoped lang="scss">
    .mod-albums {
        background-color: #fff;
        padding: 10px 17px;
    }

    .hd {
        display: flex;
        margin: 14px 0 18px 0;
    }

    .hd h2 {
        -webkit-box-flex: 1;
        -webkit-flex: 1;
        flex: 1;
        margin: 0;
        padding: 0;
        font-size: 20px;
    }

    .hd div {
        width: 64px;
        font-size: 12px;
        text-align: right;
        padding-right: 30px;
    }

    .mod-albums .gallery {
        overflow: hidden;
        margin: 0 -5px;
    }

    .mod-albums .gallery .card {
        width: 32%;
        float: left;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        padding: 0 5px 10px;
        text-align: center;
    }

    .mod-albums .gallery .card .album {
        position: relative;
    }

    .mod-albums .gallery .card img {
        display: inline-block;
        width: 100px;
        height: 100px;
        border: 1px solid #eee;
    }

    .mod-albums .gallery .card .name {
        font-size: 14px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        margin-top: 4px;
        line-height: 14px;
        max-height: 28px;
        margin-bottom: 2px;
    }


</style>