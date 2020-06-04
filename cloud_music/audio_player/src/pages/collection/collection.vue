<template>
  <!-- <transition name="slide" mode="out-in"> -->
  <div class="bc">
    <div class="top-btn">
      <van-icon class="arrow-left" name="arrow-left" @click="goBack" color="#fff" size="6.5vw" />
      <!-- <div class="top-title">
          <div class="top-collection">
            <span>我的收藏</span>
          </div>
          <div class="top-broadcast">
            <span>最近播放</span>
          </div>
      </div>-->
      <van-tabs
        v-model="activeName"
        background="#d44439"
        title-inactive-color="#eee"
        title-active-color="#fff"
        color="#fff"
        line-width="20vw"
      >
        <van-tab title="我的收藏" name="a" style="font-size:5vw">
          <div class="broad-all">
            <van-icon class="icon" name="play-circle-o" color="#1989fa" size="25" />
            <span>播放全部111</span>
          </div>
          <collect></collect>
        </van-tab>
        <van-tab title="最近播放" name="b">
          <div class="broad-all">
            <van-icon class="icon" name="play-circle-o" color="#1989fa" />
            <span>播放全部222</span>
          </div>

          <broadcast></broadcast>
        </van-tab>
      </van-tabs>
    </div>
  </div>
  <!-- </transition> -->
</template>

<script>
import collect from "./components/collect";
import broadcast from "./components/broadcast";
export default {
  data() {
    return {
      activeName: "a"
    };
  },
  components: {
    collect,
    broadcast
  },
  methods: {
    goBack() {
      this.$router.go(-1);
    }
  }
};
</script>

<style lang="scss" scoped>
@import "@/style/collection/collection.scss";
// .slide-enter-active,
// .slide-leave-active {
//     transition: all 0.3s;
// }

// .slide-enter,
// .slide-leave-to {
//     transform: translate3d(100%, 0, 0);
// }
</style>