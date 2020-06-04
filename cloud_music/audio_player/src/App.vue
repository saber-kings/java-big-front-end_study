<template>
  <div id="app">
    <!-- 路由视图 -->
    <keep-alive>
        <router-view class="view"></router-view>
    </keep-alive>
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
html,body,#app{
  width: 100%;
  height: 100%;
}
/* .app-view {
  background: yellow;
} */
/* .fold-left-enter-active {
    animation-name: fold-left-in;
    animation-duration: .2s;
  }
  .fold-left-leave-active {
    animation-name: fold-left-out;
    animation-duration: .2s;
  } */
  /* @keyframes fold-left-in {
    100% {
      -webkit-transform: translate3d(0, 0, 0);
      transform: translate3d(0, 0, 0);
    }
    0% {
      -webkit-transform: translate3d(0, 0, 0);
      transform: translate3d(0, 0, 0);
    }
  } */
  /* @keyframes fold-left-out {
    0% {
      -webkit-transform: translate3d(0, 0, 0);
      transform: translate3d(0, 0, 0);
      opacity: 1;
    }
    100% {
      -webkit-transform: translate3d(-100%, 0, 0);
      transform: translate3d(-100%, 0, 0);
      opacity: 0;
    }
  } */

</style>
