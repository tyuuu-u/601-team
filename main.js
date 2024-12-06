
// #ifndef VUE3
import Vue from 'vue'
import App from './App'
import { $http } from '@escook/request-miniprogram'
import store from './store/store.js'

uni.$http = $http
// 电脑连手机wiif,连上之后查看电脑的ip地址,把下面的地址改为电脑查到的ip地址,就可以手机wx扫码使用小程序了
// $http.baseUrl = 'http://localhost:9999'
$http.baseUrl = 'http://172.20.10.2:9999'

//请求拦截器
$http.beforeRequest = function(opt){
	uni.showLoading({
		title:'waiting..'
	})
	  // 判断请求的是否为订单接口,只有订单接口要token
	  if (opt.url.indexOf('/order/') !== -1) {
	    // 为请求头添加身份认证字段
	    opt.header = {
	      // 字段的值可以直接从 vuex 中进行获取
	      token: store.state.m_user.token,
	    }
	  }
}
//响应拦截器
$http.afterRequest = function(opt){
	uni.hideLoading()
}

uni.$showMsg = function(title = '加载失败!',duration = 1500){
	uni.showToast({
		title,
		duration,
		icon:'none',
	})
}

Vue.config.productionTip = false

App.mpType = 'app'

const app = new Vue({
    ...App,
	store,
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
import App from './App.vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif