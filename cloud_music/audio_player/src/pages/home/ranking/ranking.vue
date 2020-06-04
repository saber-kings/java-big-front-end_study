<template>
  <div>
    <!-- 排行榜 -->
    <div class="rankingbox"></div>
    <ranking-box></ranking-box>
  </div>
</template>

<script>
import rankingBox from "./components/rankingBox";
export default {
  components: {
    rankingBox
  }
};
</script>

<style lang="scss">
@import "@/style/ranking/ranking";
</style>