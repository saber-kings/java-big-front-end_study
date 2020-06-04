<template lang="html">
    <div class="mod-albums">
        <div class="hd log url">
            <h2>新歌速递</h2>
            <div>更多</div>
        </div>
        <div class="container">
            <div class="gallery">
                <div class="scroller">
                    <div class="card url" v-for="(item,index) in newSongs" :key="index">
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
        name:"NewSongs",
        data() {
            return {
                newSongs:[]
            }
        },
        mounted(){
            axios.get('v1/restserver/ting?from=webapp_music&format=json&param=3jEyQLeBhjR9Gt5wDsmHxzvWc0%2FwjUP23ntgkbgzvR5bn1YduKvyO3vArWmnb9jkxkylf%2FRAw0LMIJlmRtCMIw%3D%3D&timestamp=1575949521&sign=41543fb3ff0073ede04b1b31b2517fc0&method=baidu.ting.ugcdiy.getBaseInfo&s_protocol=1'
            ).then((res) => {
                // console.log(res)
                var songList=res.data.result.songlist;
                this.newSongs = songList.slice(0,6)

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
        margin-top: 15px;
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