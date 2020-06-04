import { ajaxGet } from "@/utils/request.js"

//获取排行接口
export function getRankIng(config) {
    let url = "/top/list"
    return ajaxGet(url,config)
}
