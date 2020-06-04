import Vue from 'vue'
import App from './App.vue'

import router from '@/router/index.js'//路由

import store from "@/store/index.js"//vuex

import '@/style/border.css' //重置样式
import '@/style/reset.css'

import Vant from 'vant'; //vant-ui
import 'vant/lib/index.css';

import { Lazyload } from 'vant'; //懒加载

import FastClick from 'fastclick' //解决移动端500毫秒延迟
FastClick.attach(document.body)

import myPlugins from "@/plugins/index.js"//自定义插件（全局组件）

import "@/style/iconfont/iconfont.css"

import { Tab, Tabs } from 'vant';//标签页
import { Divider } from 'vant'; //分割线
import { Image as VanImage } from 'vant';//图片
import { IndexBar, IndexAnchor } from 'vant';//IndexBar 索引栏
import { Empty } from 'vant';//错误信息
import { Slider } from 'vant';//滑块



Vue
    .use(Vant)
    .use(myPlugins)
    .use(Tab)
    .use(Tabs)
    .use(Lazyload)
    .use(Divider)
    .use(VanImage)
    .use(IndexBar)
    .use(IndexAnchor)
    .use(Empty)
    .use(Slider);

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
