<template>
  <div>
  
    <!-- 轮播图 -->
 
    <recommend-banner></recommend-banner>

    <!-- 推荐歌单 -->
    
    <recommend-song-list></recommend-song-list>
    
    <!-- 推荐歌曲 -->
   
    <recommend-song></recommend-song>
    
  </div>
</template>

<script>

import recommendBanner from "./components/recommendBanner";
import recommendSongList from "./components/recommendSongList";
import recommendSong from "./components/recommendSong";
export default {
  components: {
    recommendBanner,
   recommendSongList,
    recommendSong
  }
};
</script>

<style>
</style>