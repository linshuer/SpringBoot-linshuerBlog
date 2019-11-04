import Vue from 'vue'
import Router from 'vue-router'
import login from '../components/login'
import home from '../components/admin'
import edit from '../components/edit'
import userinfo from '../components/userinfo'
Vue.use(Router)

const constantRouterMap = [
  {
    path: '/login',
    name: 'login',
    component: login
  },
  {
    path: '/admin',
    name: 'admin',
    component: home
  },
  {
    path: '/edit',
    name: 'edit',
    component: edit
  },
  {
    path: '/userinfo',
    name: 'userinfo',
    component: userinfo
  }
]

export default new Router({
  mode: 'history', //后端支持可开
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap,
})
