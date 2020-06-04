<template>
    <div class="index">
        <ul>
            <li>
                <router-link to="/home">
                    <img src="../assets/logo-ea5.png" alt="">
                </router-link>
            </li>
            <li>
                <router-link to="/artists">
                    歌手
                </router-link>
            </li>
            <li>
                <router-link to="/listcate">
                    榜单
                </router-link>
            </li>
            <li>
                <router-link to="/ucenter">
                    我的
                </router-link>
            </li>
            <li>
                <router-link to="/search">
                    搜索
                </router-link>
            </li>
        </ul>
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        data() {
            return {

            }
        },
        components: {

        }
    }
</script>

<style scoped lang="scss">
    .index img{
        display: inline-block;
        width: 26px;
        height: 26px;
        padding-left: 10px;
    }

    .index ul{
        display: flex;
        height: 50px;
        line-height: 50px;
        background: #f9f9f9;
    }

    .index ul li {
        flex: 1;
       align-items: center;
    }

    .index ul li a{
        font-size: 16px;
        color: #999;
    }
</style>