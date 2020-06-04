import { ajaxGet } from "@/utils/request.js"

//获取首页推荐歌单详情页接口
export function detail(config){
    let url = "/playlist/detail"
    return ajaxGet(url,config)
}
//获取歌手详情接口
export function singerdetails(config){
    let url = "/artists"
    return ajaxGet(url,config)
}
//获取排行国电榜详情接口  需要传值idx  值为0，1，2，3，4，22，23
export function list(config){
    let url = "/top/list"
    return ajaxGet(url,config)
}