<template>
  <transition name="slide" mode="out-in">
    <div class="search">
      <div class="top">
        <van-icon class="arrow-left" name="arrow-left" @click="goBack" color="#f0f0f0" size="6vw" />
        <template>
          <input type="text" @input="getValue" class="input" placeholder="搜索歌曲，歌手，专辑" ref="inputs" />
        </template>
        <van-icon
          v-if="value"
          @click="empty"
          class="arrow-right"
          name="cross"
          color="#f0f0f0"
          size="6vw"
        />
      </div>
      <!-- 搜索后显示内容 -->
      <div v-if="searchData.length!=0" class="searchData">
        <div class="search-list" v-for="(item,idx) of searchData" :key="idx">
          <div class="search-name">{{item.name}}</div>
          <div class="search-name-list" v-for="(items,idxs) of item.artists" :key="idxs">
            <div class="search-names">{{items.name}}</div>
          </div>
        </div>
      </div>
      <!-- 搜索前显示内容 -->
      <div v-else class="hot-search">
        <div class="hot-title">
          <span>热门搜索</span>
        </div>
        <div class="hot-list">
          <div
            class="hot-list-item"
            @click="addValue(idx)"
            v-for="(item,idx) of hotList"
            :key="idx"
          >
            <div class="item-idx">{{idx+1}}</div>
            <div class="hot-name" v-if="item">{{item.first}}</div>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import * as api from "@/request/api/search.js";

export default {
  data() {
    return {
      value: "",
      hotList: [], //搜索展示的数据
      searchData: [], //搜索后展示的数据
      name: ""
    };
  },
  async created() {
    let val = await api.getHot();//获取热门搜索数据
    this.hotList = val.data.result.hots;
  },
  methods: {
    async API_getSearch() {
      let list = await api.getSearch({//获取搜索数据
        params: {//传值
          keywords: this.name
        }
      });
      return list;
    },
    async getValue() {
      //获取输入框数据
      this.name = this.$refs.inputs.value;//把input的数据赋值给name
      this.value = this.$refs.inputs.value;//把input的数据赋值给value，有值显示x按钮
      if (this.name.length > 0) {//判断name的长度大于0执行
        let list = await this.API_getSearch();//搜索接口的数据
        this.searchData = list.data.result.songs;//把搜索接口的数据赋给searchData数组
      } else {//没有输入不执行
        this.searchData = "";
      }
    },
    goBack() {
      //返回上一页
      this.$router.go(-1);
      this.$refs.inputs.value = ""; //清空输入框内容
      this.searchData = "";//清空执行后渲染显示内容
    },
    empty() {
      //点击x图标按钮
      this.$refs.inputs.value = "";//清空输入框内容
      this.searchData = "";//清空执行后渲染显示内容
    },
    async addValue(idx) {
      //点击热门搜索
      this.$refs.inputs.value = this.hotList[idx].first;//获取点击项的name赋值给输入框
      this.name = this.hotList[idx].first;//输入框拿到值赋值给name
      this.value = this.hotList[idx].first;//输入框拿到值显示x清空按钮
      if (this.name.length > 0) {//判断name的长度大于0执行
        let list = await this.API_getSearch();//搜索接口的数据
        this.searchData = list.data.result.songs;//把搜索接口的数据赋给searchData数组
      } else {//没有输入不执行
        this.searchData = "";
      }
    }
  }
};
</script>

<style scoped lang="scss">
@import "@/style/search/search.scss";
.slide-enter-active,
.slide-leave-active {
  transition: all 0.3s;
}

.slide-enter,
.slide-leave-to {
  transform: translate3d(100%, 0, 0);
}
</style>
