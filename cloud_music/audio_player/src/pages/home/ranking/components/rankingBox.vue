<template>
  <div>
    <template v-if="list" class="rankingbox">
      <van-card v-for="(item,idx) in list" :key="idx" lazy-load >
        <!-- <router-link :to="{path:'/detailsThree',query:{idx:item.idx}}"> -->
          <template #thumb>
            <van-image width="100%" height="100%" :src="item.data.playlist.coverImgUrl" lazy-load >
              <template v-slot:loading>
                <van-loading type="spinner" size="20" />
              </template>
            </van-image>
          </template>
          <template #title>
            <li>1.{{item.data.playlist.tracks[0].name}}</li>
            <li>2.{{item.data.playlist.tracks[1].name}}</li>
            <li>3.{{item.data.playlist.tracks[3].name}}</li>
          </template>
        <!-- </router-link> -->
      </van-card>
    </template>
  </div>
</template>

<script>
import * as api from "@/request/api/ranking";
export default {
  data() {
    return {
      list: []
    };
  },
  created() {
    this.getRankIng();
  },
  methods: {
    async getRankIng() {
      //获取排行榜详情信息的接口
      let arr = [0, 1, 2, 3, 4, 22, 23];
      arr.forEach(async item => {
        let res = await api.getRankIng({
          params: {
            idx: item
          }
        });
        this.list.push(res);
      });
    },
    // toDetails(idx) {
    //   this.$router.push({ path: "/detailsThree", query: { idx: idx } });
    // }
  }
};
</script>

<style lang="scss">
@import "@/style/ranking/ranking";
</style>