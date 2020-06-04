<template lang="html">

</template>

<script>
    export default {
        data(){
            return{

            }
        },

        mounted() {
        }
    }
</script>

<style scoped>

    .board{
        margin-bottom: 10px;
    }

    .panel {
        border-top: 1px solid #eee;
        position: relative;
        top: -1px;
        display: block;
        background: #fff;
    }

    .list{
        padding: 20px;
        padding-top: 0;
    }

    .panel .list li {
        height: 60px;
        border-bottom: 1px solid #eee;
        padding-left: 0;
        display: flex;
        padding-top: 10px;
    }

    .panel .list li .poster {
        position: relative;
        width: 45px;
        margin-right: 8px;
    }

    .panel .list li .poster img {
        border: 1px solid #eee;
    }
    .info{
        flex: 1;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    .info .name {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        font-size: 16px;
        color: #333;
    }

    .info .author {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        font-size: 12px;
        color: #999;
        margin-top: 2px;
    }

    .more-songs {
        color: #999;
        margin-top: 9px;
        font-size: 12px;
        text-align: center;
        height: 32px;
        line-height: 32px;
    }


</style>
