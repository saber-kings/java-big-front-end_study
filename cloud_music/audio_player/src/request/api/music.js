import { ajaxGet } from "@/utils/request.js"

//获取排行接口
export function url(config){
    let url = "/music/url"
    return ajaxGet(url,config)
}
//歌曲信息 歌曲图片
export function img(config){
    let url = "/song/detail"
    return ajaxGet(url,config)
}