import { ajaxGet } from '@/utils/request.js'
import axios from 'axios'

//获取歌手列表接口
export function artists() {
    let url = "https://www.fastmock.site/mock/48b335ed825d87f99bf045d1e89cc643/text/singerlist"
    return axios.get(url)
}



//获取歌手详情接口
export function singerdetails(config){
    let url = "/artists"
    return ajaxGet(url,config)
}