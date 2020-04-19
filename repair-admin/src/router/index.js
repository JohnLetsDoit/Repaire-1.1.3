import Vue from 'vue'
import Router from 'vue-router'
import { getRoutes, getRoles,getRoleById, addRole, deleteRole, updateRole, getRoleUrl,getRouteByRole} from '@/api/role'
import path from 'path'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/* Router Modules */
import componentsRouter from './modules/components'
import chartsRouter from './modules/charts'
import tableRouter from './modules/table'
import nestedRouter from './modules/nested'
import {validAlphabets} from "../utils/validate"
// import role from '@/views/permission/role'
import directive from '@/views/permission/info'
import index from "@/views/permission/page"
import {asyncRoutes} from "../../mock/role/routes";

/*
{
    path: '/user',
    component: Layout,
    redirect: '/user/info',
    alwaysShow: true, // will always show the root menu
    name: '用户管理',
    meta: {
      title: '用户管理',
      icon: 'lock',
      roles: ['admin', 'editor'] // you can set roles in root nav
    },
    children: [
      {
        path: 'info',
        component: () => import('@/views/permission/page'),
        name: '用户管理',
        meta: {
          title: '用户管理',
          roles: ['admin'] // or you can only set roles in sub nav
        }
      },
      {
        path: 'directive',
        component: () => import('@/views/permission/directive'),
        name: 'DirectivePermission',
        meta: {
          title: '菜单管理'
          // if do not set roles, means: this page does not require permission
        }
      },
    ]
 */
export  function common() {
  asyncRoutes.forEach(item => {
    const paths = []
    if (item.children) {
      paths.push(item.path)
      item.children.forEach(c => {
        paths.push(item.path + '/' + c.path)
      })
    }
    return paths
  })

}
export async function roleUrl() {
  const res = await getRoleUrl()
  // console.log(res)
  const paths = res.data.url
  console.log(res.data)
  const role = res.data.role
  paths.forEach(item => {
     pushMeta(item,role)
  })
  return true
}
export function pushMeta(url,role) {
  asyncRoutes.forEach(item => {
    const paths = []
    if (item.children){
      paths.push(item.path)
      item.children.forEach(c => {
         paths.push(item.path + '/' +c.path)
      })
    }


    if (paths.forEach( p => {
      p == url
    }) ){
      if (!(item.meta.roles.includes(role))){
        item.meta.roles.push(role)
      }
    }

  })
}

export function delMeta(url,role) {
  var index = 0
  var delIndex =0
  asyncRoutes.forEach(item => {

    /**
     *
     * @type {Array}
     */
    const paths = []
    if (item.children){
      paths.push(item.path)
      item.children.forEach(c => {
        paths.push(item.path + '/' +c.path)
      })
    }
  //————————————————end——————————————————————

          index = index+1
          if (res == role){
            delIndex = index
            item.meta.roles.splice(delIndex-1,1)
            return
          }
  })
}



export async function getRoutesT() {
  const routes =[]
  const  res = await  getRoutes()
  // this.serviceRoutes = res.data.routes
  routes.push(generateRoutesT(res.data))
  // console.log(routes)
  return routes
}

export  function generateRoutesT(routes, basePath = '/') {
  const res = []
  // console.log(res)
  for (let route of routes) {
    // skip some route
    if (route.hidden) { continue }

    const onlyOneShowingChild = onlyOneShowingChildT(route.children, route)

    if (route.children && onlyOneShowingChild && !route.alwaysShow) {
      route = onlyOneShowingChild
    }

    const data = {
      path: path.resolve(basePath, route.path),
      title: route.meta && route.meta.title

    }

    // recursive child routes
    if (route.children) {
      data.children = generateRoutesT(route.children, data.path)
    }
    res.push(data)
  }
  // console.log(res)
  return res
}

export  function onlyOneShowingChildT(children = [], parent) {
  let onlyOneChild = null
  const showingChildren = children.filter(item => !item.hidden)

  // When there is only one child route, the child route is displayed by default
  if (showingChildren.length === 1) {
    onlyOneChild = showingChildren[0]
    onlyOneChild.path = path.resolve(parent.path, onlyOneChild.path)
    return onlyOneChild
  }

  // Show parent if there are no child route to display
  if (showingChildren.length === 0) {
    onlyOneChild = { ... parent, path: '', noShowingChildren: true }
    return onlyOneChild
  }

  return false
}


/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    noCache: true                if set true, the page will no be cached(default is false)
    affix: true                  if set true, the tag will affix in the tags-view
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: () => import('@/views/redirect/index')
      }
    ]
  },

  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
  {
    path: '/kongbai',
    component: () => import('@/views/permission/kongbai'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/index',
    children: [
      {
        path: 'index',
        component: () => import('@/views/table/complex-table'),
        name: '首页',
        meta: { title: '首页', icon: 'dashboard', affix: true }
      }
    ]
  }
]

  // {
  //   path: '/documentation',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'index',
  //       component: () => import('@/views/documentation/index'),
  //       name: 'Documentation',
  //       meta: { title: 'Documentation', icon: 'documentation', affix: true }
  //     }
  //   ]
  // },
  // {
  //   path: '/guide',
  //   component: Layout,
  //   redirect: '/guide/index',
  //   children: [
  //     {
  //       path: 'index',
  //       component: () => import('@/views/guide/index'),
  //       name: 'Guide',
  //       meta: { title: 'Guide', icon: 'guide', noCache: true }
  //     }
  //   ]
  // },
  // {
  //   path: '/profile',
  //   component: Layout,
  //   redirect: '/profile/index',
  //   hidden: true,
  //   children: [
  //     {
  //       path: 'index',
  //       component: () => import('@/views/profile/index'),
  //       name: 'Profile',
  //       meta: { title: 'Profile', icon: 'user', noCache: true }
  //     }
  //   ]
  // }


/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export  function getRouter() {
  return new Promise(async resolve => {
    const res = await getRoutes()
    resolve(res.data)
  })
}

export async function getAllRoutes() {
  const res = await getRoutes()
  return buildRouters(res.data)
}

export function roleforeach(roles) {
    roles.forEach( async item => {
      let routeList = [];
      const res = await getRoleById(item.roleId)
      routeList.concat(res.data.routes)
      return routeList
    })
}
export async function getRoutesByRoles(roles) {

  // const routes = await getRouteByRole(roles)
  let roleIds = []
    roles.forEach( res => {
      roleIds.push(res.roleId)
    })

    const res = await getRouteByRole(roleIds.toLocaleString())
    return buildRouters(res.data)
  }
  // return buildRouters(routes)


export  function buildRouters(routes) {
  const asyncRoutes =[]
  routes.forEach(item => {
    const oRoute = {
      meta: {},
      children:[]
    }
    console.log(item)
    if (item.children == null){
      item.children = oRoute.children
    }

    if (!item.isChild && item.children.length >0){
      oRoute.component = Layout
      oRoute.path = item.path
      oRoute.meta = item.meta
      oRoute.name = item.name
      oRoute.redirect = item.redirect
      oRoute.hidden =item.hidden
      item.children.forEach( res => {
        const cRoute ={
          meta: {},
          children:[]
        }
        console.log('resfroeach')
        console.log(res)
        cRoute.path = res.path
        const a = res.component
        cRoute.component = () => import(`@/${a}.vue`)
        // console.log(cRoute.component)
        // cRoute.component = resolve => require([`@/views/${res.component}`],resolve)
        cRoute.name = res.name
        cRoute.meta = res.meta
        oRoute.children.push(cRoute)
      })
      asyncRoutes.push(oRoute)
    }else if (!item.isChild) {
      oRoute.component = Layout
      oRoute.path = item.path
      oRoute.meta = item.meta
      oRoute.name = item.name
      oRoute.redirect = item.redirect
      oRoute.hidden =item.hidden
      asyncRoutes.push(oRoute)
    }
    //   if (item.children.size >0){
    //   buidRouters(item.children)
    // }

  })
  console.log("asyncRoutes:::")
  console.log(asyncRoutes)
  return asyncRoutes
}
// export const asyncRoutes =  getRouter()

// export const asyncRoutes = [
//
//
//   {
//     path: '/user',
//     component: Layout,
//     redirect: '/user/info',
//     alwaysShow: true, // will always show the root menu
//     name: '用户管理',
//     meta: {
//       title: '用户管理',
//       icon: 'lock',
//       roles: ['admin', 'editor'] // you can set roles in root nav
//     },
//     children: [
//       {
//         path: 'info',
//         component: () => import('@/views/permission/info'),
//         name: '用户管理',
//         meta: {
//           title: '用户管理',
//           roles: ['admin'] // or you can only set roles in sub nav
//         }
//       },
//       {
//         path: 'directive',
//         component: () => import('@/views/permission/directive'),
//         name: 'DirectivePermission',
//         meta: {
//           title: '菜单管理'
//           // if do not set roles, means: this page does not require permission
//         }
//       },
//       {
//         path: 'role',
//         component: () => import('@/views/permission/role'),
//         name: '角色管理',
//         meta: {
//           title: '角色管理',
//           icon: 'icon',
//           roles: ['admin']
//         }
//       }
//     ]
//   },
//
//
//   /** when your routing map is too long, you can split it into small modules **/
//
//   {
//     path: '/tree',
//     component: Layout,
//     redirect: '/tree/index',
//     alwaysShow: true, // will always show the root menu
//     name: '故障树管理',
//     meta: {
//       title: '故障树管理',
//       icon: 'tree',
//       roles: ['admin', 'editor'] // you can set roles in root nav
//     },
//     children: [
//       {
//         path: 'index',
//         component: () => import('@/views/tree/index'),
//         name: '报修故障树',
//         meta: { title: '故障管理', icon: 'tree', noCache: true }
//       },
//       {
//         path: 'index2',
//         component: () => import('@/views/tree/index'),
//         name: '报修故障树',
//         meta: { title: '故障添加', icon: 'tree', noCache: true }
//       }
//
//     ]
//   },
//   chartsRouter,
//   // {
//   //   path: '/example',
//   //   component: Layout,
//   //   redirect: '/example/list',
//   //   name: 'Example',
//   //   meta: {
//   //     title: 'Example',
//   //     icon: 'example'
//   //   },
//   //   children: [
//   //     {
//   //       path: 'create',
//   //       component: () => import('@/views/example/create'),
//   //       name: 'CreateArticle',
//   //       meta: { title: 'Create Article', icon: 'edit' }
//   //     },
//   //     {
//   //       path: 'edit/:id(\\d+)',
//   //       component: () => import('@/views/example/edit'),
//   //       name: 'EditArticle',
//   //       meta: { title: 'Edit Article', noCache: true, activeMenu: '/example/list' },
//   //       hidden: true
//   //     },
//   //     {
//   //       path: 'list',
//   //       component: () => import('@/views/example/list'),
//   //       name: 'ArticleList',
//   //       meta: { title: 'Article List', icon: 'list' }
//   //     }
//   //   ]
//   // },
//   //
//   // {
//   //   path: '/tab',
//   //   component: Layout,
//   //   children: [
//   //     {
//   //       path: 'index',
//   //       component: () => import('@/views/tab/index'),
//   //       name: 'Tab',
//   //       meta: { title: 'Tab', icon: 'tab' }
//   //     }
//   //   ]
//   // },
//   //
//   // {
//   //   path: '/error',
//   //   component: Layout,
//   //   redirect: 'noRedirect',
//   //   name: 'ErrorPages',
//   //   meta: {
//   //     title: 'Error Pages',
//   //     icon: '404'
//   //   },
//   //   children: [
//   //     {
//   //       path: '401',
//   //       component: () => import('@/views/error-page/401'),
//   //       name: 'Page401',
//   //       meta: { title: '401', noCache: true }
//   //     },
//   //     {
//   //       path: '404',
//   //       component: () => import('@/views/error-page/404'),
//   //       name: 'Page404',
//   //       meta: { title: '404', noCache: true }
//   //     }
//   //   ]
//   // },
//   //
//   // {
//   //   path: '/error-log',
//   //   component: Layout,
//   //   children: [
//   //     {
//   //       path: 'log',
//   //       component: () => import('@/views/error-log/index'),
//   //       name: 'ErrorLog',
//   //       meta: { title: 'Error Log', icon: 'bug' }
//   //     }
//   //   ]
//   // },
//   //
//   // {
//   //   path: '/excel',
//   //   component: Layout,
//   //   redirect: '/excel/export-excel',
//   //   name: 'Excel',
//   //   meta: {
//   //     title: 'Excel',
//   //     icon: 'excel'
//   //   },
//   //   children: [
//   //     {
//   //       path: 'export-excel',
//   //       component: () => import('@/views/excel/export-excel'),
//   //       name: 'ExportExcel',
//   //       meta: { title: 'Export Excel' }
//   //     },
//   //     {
//   //       path: 'export-selected-excel',
//   //       component: () => import('@/views/excel/select-excel'),
//   //       name: 'SelectExcel',
//   //       meta: { title: 'Export Selected' }
//   //     },
//   //     {
//   //       path: 'export-merge-header',
//   //       component: () => import('@/views/excel/merge-header'),
//   //       name: 'MergeHeader',
//   //       meta: { title: 'Merge Header' }
//   //     },
//   //     {
//   //       path: 'upload-excel',
//   //       component: () => import('@/views/excel/upload-excel'),
//   //       name: 'UploadExcel',
//   //       meta: { title: 'Upload Excel' }
//   //     }
//   //   ]
//   // },
//   //
//   // {
//   //   path: '/zip',
//   //   component: Layout,
//   //   redirect: '/zip/download',
//   //   alwaysShow: true,
//   //   name: 'Zip',
//   //   meta: { title: 'Zip', icon: 'zip' },
//   //   children: [
//   //     {
//   //       path: 'download',
//   //       component: () => import('@/views/zip/index'),
//   //       name: 'ExportZip',
//   //       meta: { title: 'Export Zip' }
//   //     }
//   //   ]
//   // },
//   //
//   // {
//   //   path: '/pdf',
//   //   component: Layout,
//   //   redirect: '/pdf/index',
//   //   children: [
//   //     {
//   //       path: 'index',
//   //       component: () => import('@/views/pdf/index'),
//   //       name: 'PDF',
//   //       meta: { title: 'PDF', icon: 'pdf' }
//   //     }
//   //   ]
//   // },
//   // {
//   //   path: '/pdf/download',
//   //   component: () => import('@/views/pdf/download'),
//   //   hidden: true
//   // },
//   //
//   // {
//   //   path: '/theme',
//   //   component: Layout,
//   //   children: [
//   //     {
//   //       path: 'index',
//   //       component: () => import('@/views/theme/index'),
//   //       name: 'Theme',
//   //       meta: { title: 'Theme', icon: 'theme' }
//   //     }
//   //   ]
//   // },
//
//   // {
//   //   path: '/clipboard',
//   //   component: Layout,
//   //   children: [
//   //     {
//   //       path: 'index',
//   //       component: () => import('@/views/clipboard/index'),
//   //       name: 'ClipboardDemo',
//   //       meta: { title: 'Clipboard', icon: 'clipboard' }
//   //     }
//   //   ]
//   // },
//   //
//   // {
//   //   path: 'external-link',
//   //   component: Layout,
//   //   children: [
//   //     {
//   //       path: 'https://github.com/PanJiaChen/vue-element-admin',
//   //       meta: { title: 'External Link', icon: 'link' }
//   //     }
//   //   ]
//   // },
//
//   // 404 page must be placed at the end !!!
//   { path: '*', redirect: '/404', hidden: true }
// ]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router

