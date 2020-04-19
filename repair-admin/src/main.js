import Vue from 'vue'

import Cookies from 'js-cookie'

import 'normalize.css/normalize.css' // a modern alternative to CSS resets

import Element from 'element-ui'
import './styles/element-variables.scss'

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

import './icons' // icon
import './permission' // permission control
import './utils/error-log' // error log

import * as filters from './filters' // global filters

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */
// import { mockXHR } from '../mock'
// if (process.env.NODE_ENV === 'production') {
//   mockXHR()
// }


Vue.use(Element, {
  size: Cookies.get('size') || 'medium' // set element-ui default size
})

// register global utility filters
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = false

/**
 * 动态添加meta
 */
// router.beforeEach((to, from, next) => {
//   if (to.meta.metaInfo)
//     store.commit("CAHNGE_META_INFO", to.meta.metaInfo)
//   next()
// });


new Vue({
  el: '#app',
  router,
  store,
  /**
   * 动态添加meta
   * @returns {{meta: *[], title: *}}
   */
  // metaInfo(){
  //   return {
  //     title: this.$store.state.metaInfo.title,
  //     meta: [
  //       {
  //         name: "keywords",
  //         content: this.$store.state.metaInfo.keywords
  //       }, {
  //         name: "description",
  //         content: this.$store.state.metaInfo.description
  //       }
  //     ]
  //   }
  // },

  //-------------------------分割线--------------------------------
  render: h => h(App)
})
