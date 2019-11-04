import Vue from 'vue'
import Vuex from 'vuex'
import * as getters from './getters'
import * as  mutations from './mutations'
import * as actions from './actions'

Vue.use(Vuex);


const store = new Vuex.Store({
  //定义状态
 state:{
   currentUser:null,   //当前用户
   isLogin:false    //判断当前用户是否已经登录
 },
  getters,
  mutations,
  actions
});

export default store
