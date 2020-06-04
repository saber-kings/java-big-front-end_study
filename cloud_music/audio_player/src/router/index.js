import Vue from "vue"
import VueRouter from "vue-router"
import notFout from "@/pages/notFout/notFout"//404
import home from "@/pages/home/home"//首页
import details from "@/pages/details/details"//歌单详情页
import detailsTwo from "@/pages/details/detailsTwo"//歌手详情页
import detailsThree from "@/pages/details/detailsThree"//排行详情页
import recommend from "@/pages/home/recommend/recommend" //排行榜
import ranking from "@/pages/home/ranking/ranking" //排行榜
import singer from "@/pages/home/singer/singer" //歌手
import music from "@/pages/music/music" //歌曲播放器
import collection from "@/pages/collection/collection" //收藏
import search from "@/pages/search/search" //搜索

Vue.use(VueRouter)

let routes = [
    {
        path: "/home",
        alias: "/",
        name: "home",
        component: home,
        meta: {
            keepAlive: true //需要被缓存的组件
        },
        children: [
            {
                path: '/recommend',
                alias: "/",
                name: "recommend",
                component: recommend
            },
            {
                path: '/ranking',
                name: "ranking",
                component: ranking
            },
            {
                path: '/singer',
                name: 'singer',
                component: singer
            }
        ]
    },
    {
        path: "/details",
        name: "details",
        component: details,
        props: function (route) {
            return { id: route.query.id }
        },
        meta: {
            keepAlive: true //需要被缓存的组件
        }
    },
    {
        path: "/detailsTwo",
        name: "detailsTwo",
        component: detailsTwo,
        props: function (route) {
            return { id: route.query.id }
        },
        meta: {
            keepAlive: true //需要被缓存的组件
        }
    },
    {
        path: "/detailsThree",
        name: "detailsThree",
        component: detailsThree,
        props: function (route) {
            return { idx: route.query.idx }
        },
        meta: {
            keepAlive: true //需要被缓存的组件
        }
    },
    {
        path: '/music',
        name: 'music',
        component: music,
        meta: {
            keepAlive: false //需要被缓存的组件
        }
    },
    {
        path: "/collection",
        name: "collection",
        component: collection,
        props: function (route) {
            return { idx: route.query.idx }
        },
        meta: {
            keepAlive: true //需要被缓存的组件
        }
    },
    {
        path: "/search",
        name: "search",
        component: search,
        props: function (route) {
            return { idx: route.query.idx }
        },
        meta: {
            keepAlive: true //需要被缓存的组件
        }
    },
    {
        path: "*",
        name: "notFout",
        component: notFout
    }
]

export default new VueRouter({
    routes,
    scrollBehavior() {
        return { x: 0, y: 0 }
    }
})