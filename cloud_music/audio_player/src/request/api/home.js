import { ajaxGet } from "@/utils/request.js"

//获取轮播图接口
export function getIndexInfo(config) {
    let url = "/banner"
    return ajaxGet(url,config)
}

//获取首页推荐歌单接口
export function personalized(config){
    let url = "/personalized"
    return ajaxGet(url,config)
}

//获取首页推荐歌曲接口
export function newsong(config){
    let url = "/personalized/newsong"
    return ajaxGet(url,config)
}

