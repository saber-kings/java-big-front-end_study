<template>
  <div class="swipebox">
    <van-swipe :autoplay="3000" class="swipe">
      <van-swipe-item v-for="(item,idx) in listBanner" :key="idx">
          <router-link :to="{path:'/details',query:{id:item.targetId}}">
        <van-image class="img" :src="item.picUrl" lazy-load/>
          </router-link>
      </van-swipe-item>
    </van-swipe>
  </div>
</template>

<script>
import * as api from "@/request/api/home.js";
export default {
  data() {
    return {
      listBanner: []
    };
  },
  async created() {
    let banner = await api.getIndexInfo();
    if (banner.data.code === 200) {
      this.listBanner = banner.data.banners;
    }
  }
};
</script>

<style lang="scss">
@import "@/style/home/homeBanner";
</style>