<template>
  <div class="songlist">
    <h2>推荐歌单</h2>
    <van-grid :border="false" :column-num="3" class="list">
      <van-grid-item v-for="item in songlist" :key="item.id">
        <div class="img-list">
          <router-link :to="{path:'/details',query:{id:item.id}}">
            <van-image :src="item.picUrl" lazy-load>
              <template v-slot:loading>
                <van-loading type="spinner" size="20" />
              </template>
            </van-image>
            <span>{{item.name}}</span>
          </router-link>
        </div>
      </van-grid-item>
    </van-grid>
  </div>
</template>

<script>
import * as api from "@/request/api/home.js";
export default {
  data() {
    return {
      songlist: []
    };
  },
  async created() {
    let list = await api.personalized();
    if (list.data.code === 200) {
      this.songlist = list.data.result;
      // console.log(this.songlist);
    }
  }
};
</script>

<style lang="scss">
@import "@/style/home/homeSongList";
</style>