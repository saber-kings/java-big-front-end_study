<template>
  <div class="details">
    <!-- 详情页上部分 -->
    <div class="details-top">
      <van-image :src="dataInfoTwo.img1v1Url" lazy-load>
        <template v-slot:loading>
          <van-loading type="spinner" size="80" />
        </template>
      </van-image>
      <div class="top-btn">
        <van-icon class="arrow-left" name="arrow-left" @click="goBack" color="#fff" size="8vw" />
        <p>歌手</p>
      </div>
      <div class="top-text">
        <h2>{{dataInfoTwo.name}}</h2>
        <!-- <div class="ting">
          <van-icon class="service" name="service" color="#fff" size="4.5vw" />
          <p>{{Math.round(dataInfo.playCount/10000)}}万</p>
        </div>-->
      </div>
    </div>
    <!-- 详情页下部分 -->

    <div class="details-bottom">
      <div class="btm-title">
        <van-icon name="play-circle-o" size="6vw" />
        <span>播放全部</span>
        <span class="sum" v-if="dataInfo">(共{{dataInfo.hotSongs.length}}首)</span>
      </div>
      <div class="btm-content">
        <div class="ranking" v-for="(item,idx) of dataInfo.hotSongs" :key="idx">
          <div class="ranking-left">{{idx+1}}</div>
          <div class="ranking-right" v-on:click="toMusic(idx)">
            <p class="right-name">{{item.name}}</p>
            <p>{{item.ar[0].name}}</p>
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
      dataInfo: "",
      dataInfoTwo: ""
    };
  },
  async activated() {
    this.singerdetails();
  },
  methods: {
    goBack() {
      this.dataInfo = "";
      this.dataInfoTwo = "";
      this.$router.go(-1);
    },

    async singerdetails() {
      let res = await api.singerdetails({
        params: {
          id: this.id
        }
      });
      if (res.data.code === 200) {
        this.dataInfo = res.data;
        this.dataInfoTwo = res.data.artist;
        console.log(this.dataInfo.hotSongs);
      }
    },

    toMusic: function(idx) {
      this.$router.push({
        path: "music",
        query: { id: this.dataInfo.hotSongs[idx].id }
      });
    }
  }
};
</script>

<style lang="scss">
@import "@/style/details/details.scss";
</style>