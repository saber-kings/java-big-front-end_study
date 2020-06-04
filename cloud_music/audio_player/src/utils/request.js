import httpRequest from "@/request/config.js"

export function ajaxGet(url, config) { //get封装
    return httpRequest.get(url, config)
        .then(function (res) {
            return res
        })
}

export function ajaxPost(url, data, config) { //post封装
    return httpRequest.post(url, data, config)
        .then(function (res) {
            return res
        })
}