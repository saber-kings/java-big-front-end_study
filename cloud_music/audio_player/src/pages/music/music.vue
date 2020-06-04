<template>
  <div class="bg">
    <!-- 歌名 -->
    <div class="musicTop">
      <span>
        <van-icon name="arrow-down" size="20" @click="go" />
      </span>
      <p v-if="songName[0]">{{songName[0].name}}</p>
      <p v-if="songName[0]">{{songName[0].ar[0].name}}</p>
    </div>

    <!-- 图片旋转部分 -->
    <div class="musicImg">
      <div class="imgbox" v-if="songName[0]">
        <van-image round width="65vw" height="65vw" :src="songName[0].al.picUrl">
          <template v-slot:loading>
            <van-loading type="spinner" size="20" />
          </template>
        </van-image>
      </div>
    </div>

    <!-- 主键 按钮控制 -->
    <div class="musicFoot">
      <div class="jindu">
        <span>{{this.uptime}}</span>
        <van-slider v-model="value" @change="onChange" class="progressBar" />
        <span>{{this.long}}</span>
      </div>

      <ul class="musicUl">
        <li>
          <!-- 列表播放 -->
          <span class="iconfont icon-gongnengtubiao-18"></span>
        </li>
        <!-- <li>
          随机播放
          <span class="iconfont icon-gongnengtubiao-15"></span>
        </li>
         <li>
          单曲循环
          <span class="iconfont icon-gongnengtubiao-177"></span>
        </li>-->

        <li>
          <!-- 上一首 -->
          <span class="iconfont icon-gongnengtubiao-03"></span>
        </li>
        <li @click="onPlay">
          <!-- 播放 -->
          <span class="iconfont icon-gongnengtubiao-07" id="play"></span>
        </li>
        <li>
          <!-- 下一首 -->
          <span class="iconfont icon-gongnengtubiao-04"></span>
        </li>

        <li>
          <!-- 收藏 -->
          <span class="iconfont icon-gongnengtubiao-63"></span>
        </li>
      </ul>
      <audio id="audio" autoplay :src="songList"></audio>
    </div>
  </div>
</template>

<script>
import * as api from "@/request/api/music.js";
export default {
  // props: ["id"],
  data() {
    return {
      value: 0,
      bool: true, //开关
      uptime: "",
      long: "",
      second: "0",
      minute: "0",
      songList: [], //歌曲地址
      songName: [], //歌曲名字和图片
      songArr: []
    };
  },
  async activated() {
    let res = await this.getSongList();
    this.songList = res.data[0].url;
    let ress = await this.getSongArr();
    this.songName = ress;

    // console.log(this.$route.query.arr)
    this.upSchedule();
  },
  methods: {
    // 自动更新进度
    upSchedule() {
      let audio = document.getElementById("audio");
      let audioTime = audio.duration | 0; //获取回来的歌曲时长
      let theTime = parseInt(audioTime); // 秒
      let middle = 0; // 分
      if (theTime > 60) {
        middle = parseInt(theTime / 60);
        theTime = parseInt(theTime % 60);
        return (this.long = middle + ":" + theTime);
      }

      setInterval(() => {
        if (this.bool) {
          let time = ((audio.currentTime / audio.duration) * 100) | 0;
          return (this.value = time);
        }
      }, 1000);

      setInterval(() => {
        this.second++;
        if (this.second > 60) {
          this.minute++;
          this.second = 0;
        }
        return (this.uptime = this.minute + ":" + this.second);
      }, 1000);
    },

    // 改变进度条
    onChange(value) {
      let audio = document.getElementById("audio");
      let audioLong = audio.duration | 0; //歌曲时长
      let audioSchedule = audio.currentTime; //当前进度
      console.log(audioLong);
      console.log(audioSchedule);
      console.log(value);
    },

    // 播放、暂停
    onPlay() {
      let audio = document.getElementById("audio");
      // console.log(this);
      let play = document.getElementById("play");
      let imgbox = document.getElementsByClassName("imgbox")[0];
      if (audio.paused) {
        audio.play();
        play.className = "iconfont icon-gongnengtubiao-07";
        imgbox.style.animationPlayState = "running";
      } else {
        audio.pause();
        play.className = "iconfont icon-gongnengtubiao-06";
        imgbox.style.animationPlayState = "paused";
      }
    },

    async getSongList() {
      let ListRes = await api.url({
        params: {
          id: this.$route.query.id
        }
      });
      if (ListRes.data.code === 200) {
        // console.log(ListRes.data)
        return ListRes.data;
      }
    },

    async getSongArr() {
      let res = await api.img({
        params: {
          ids: this.$route.query.id
        }
      });
      if (res.data.code === 200) {
        return res.data.songs;
      }
    },
    go() {
      this.$router.go(-1);
      this.bool = false;
    }
  }
};
</script>

<style lang="scss">
@import "@/style/music/music";
</style>