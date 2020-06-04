import Vue from "vue";
import VueRouter from "vue-router";
import Index from '@/pages/index';
import Home from '@/pages/home';
import Artists from '@/pages/artists';
import Listcate from '@/pages/listcate';
import Search from '@/pages/search';
import Ucenter from '@/pages/ucenter';
import HotList from  "@/pages/musiclist/hot_list";
import KingList from  "@/pages/musiclist/king_list";
import NewList from  "@/pages/musiclist/new_list";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "index",
      redirect:"/home",
    component: Index,
      children:[
          {
              path:"home",
              component: Home,
              redirect:"/home/hot",
              children:[
                  {
                      path:"hot",
                      component: HotList
                  },
                  {
                      path:"king",
                      component: KingList
                  },
                  {
                      path:"new",
                      component: NewList
                  }
              ]
          },
          {
              path:"artists",
              component: Artists
          },
          {
              path:"listcate",
              component: Listcate
          },
          {
              path:"search",
              component: Search
          },
          {
              path:"ucenter",
              component: Ucenter
          }
      ]
  },
];

const router = new VueRouter({
  routes
});

export default router;
