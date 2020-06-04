<template>
  <!-- 头部 -->
  <div class="music-header">
    <van-nav-bar title="Cloud Music" class="box" tag="div">
      <template #left>
        <router-link to="/collection" class="mine">
          <van-icon class="music-o" name="music-o" color="#fff" size="6vw" />
        </router-link>
      </template>

      <template #right>
        <router-link to="/search" class="mine" tag="div">
          <van-icon class="search" name="search" color="#fff" size="6vw" />
        </router-link>
      </template>
    </van-nav-bar>

    <!-- 分栏 -->
    <van-tabs
      class="title-nav box1"
      v-model="activeName"
      background="#d44439"
      title-inactive-color="#eee"
      title-active-color="#fff"
      color="#fff"
      line-width="12vw"
    >
      <van-tab title="推荐" to="/recommend" name="recommend"></van-tab>

      <van-tab title="歌手" to="/singer" name="singer"></van-tab>

      <van-tab title="排行" to="/ranking" name="ranking"></van-tab>
    </van-tabs>
    <div class="ysbox"></div>
    <keep-alive>
      <router-view></router-view>
    </keep-alive>
  </div>
</template>

<script>
export default {
  name: "MusicHeader",
  data() {
    return {
      activeName: this.$route.name
    };
  },
  methods: {
    toDetails() {
      this.$router.push({ path: "/collection" });
    }
  }
};
</script>

<style lang="scss" >
@import "@/style/topBar/topBar";
</style>