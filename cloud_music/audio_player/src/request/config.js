import axios from "axios"

let httpRequest = axios.create({
    // baseURL: "http://musicapi.leanapp.cn",
    baseURL: "/api",
    timeout: 5000
});


export default httpRequest