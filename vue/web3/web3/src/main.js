// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'element-ui/lib/index.js'
import VueResource from 'vue-resource'
import router from './router'
import '@/icons' // icon
import $ from 'jquery'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import axios from 'axios'
import Qs from 'qs'
import store from './vuex/store'

Vue.use(ElementUI)
Vue.use(VueResource)
Vue.use(VueQuillEditor)

Vue.config.productionTip = false
Vue.http.options.emulateJSON = true

Vue.prototype.$axios = axios
Vue.prototype.qs = Qs;
Vue.prototype.$store = store

axios.defaults.timeout = 5000;
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
axios.defaults.baseURL = 'http://localhost:8080/';


//axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
// axios.defaults.headers.post['Content-Type'] = 'json';
// axios.defaults.baseURL = 'http://localhost:8080/项目名/';



/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>',
})
