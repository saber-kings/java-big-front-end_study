import { ajaxGet } from "@/utils/request.js"
//获取搜索接口
export function getSearch(config){
    let url = "/search"
    return ajaxGet(url,config)
}
// 热门搜索
export function getHot(config){
    let url = "/search/hot"
    return ajaxGet(url,config)
}
//搜索建议
export function getSuggest(config){
    let url = "/search/suggest"
    return ajaxGet(url,config)
}

