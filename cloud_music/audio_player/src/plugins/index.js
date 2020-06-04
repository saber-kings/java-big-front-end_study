import topBar from "@/components/topBar"

let myPlugins ={}

myPlugins.install = function(Vue){
    Vue.component("topBar",topBar) //底部菜单栏

   

}

export default myPlugins