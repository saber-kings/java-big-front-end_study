<template>
  <div class="details">
    <!-- 详情页上部分 -->
    <div class="details-top">
      <van-image :src="dataInfo.coverImgUrl" lazy-load>
        <template v-slot:loading>
          <van-loading type="spinner" size="80" />
        </template>
      </van-image>
      <van-sticky :offset-top="0">
        <div class="top-btn">
          <van-icon class="arrow-left" name="arrow-left" @click="goBack" color="#fff" size="6vw" />
          <p>歌单</p>
        </div>
      </van-sticky>
      <div class="top-text">
        <h2>{{dataInfo.name}}</h2>
        <div class="ting">
          <van-icon class="service" name="service" color="#fff" size="4.5vw" />
          <p>{{Math.round(dataInfo.playCount/10000)}}万</p>
        </div>
      </div>
    </div>
    <!-- 详情页下部分 -->
    <div class="details-bottom">
      <div class="btm-title">
        <van-icon name="play-circle-o" size="6vw" />
        <span>播放全部</span>
        <span class="sum" v-if="dataInfo">(共{{dataInfo.tracks.length}}首)</span>
      </div>
      <div class="btm-content">
        <div class="ranking" v-for="(item,idx) of dataInfo.tracks" :key="idx">
          <div class="ranking-left">{{idx+1}}</div>
          <div class="ranking-right">
            <p class="right-name">{{item.name}}</p>
            <p>{{item.artists[0].name}}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import * as api from "@/request/api/details.js";
export default {
  props: ["id"],
  data() {
    return {
      dataInfo: ""
    };
  },
  async activated() {
    this.detail();
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    },

    async detail() {
      let res = await api.detail({
        params: {
          id: this.id
        }
      });
      if (res.data.code === 200) {
        this.dataInfo = res.data.result;
        // console.log(res)
      }
    }
  }
};
</script>

<style lang="scss">
@import "@/style/details/details.scss";
</style>