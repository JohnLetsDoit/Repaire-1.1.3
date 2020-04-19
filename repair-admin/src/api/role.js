import request from '@/utils/request'

// export function getRoutes() {
//   return request({
//     url: '/routes',
//     method: 'get'
//   })
// }
export function getRoutes() {
  return request({
    url: '/route/getRouteList',
    method: 'get'
  })
}

export function getRouteByRole(roleIds) {
  return request({
    url: '/route/getRouteByRole',
    method: 'get',
    params: {
      roleIds: roleIds
    }
  })
}
export function getRoutesWithPage(pageNum,pageSize) {
  return request({
    url: '/route/getRouteListWithPage',
    method: 'get',
    params:{
      pageNum : pageNum,
      pageSize: pageSize
    }
  })
}
export function getRoleUrl() {
  return request({
    url: '/roleurl',
    method: 'get'
  })
}

// export function getRoles() {
//   return request({
//     url: '/roles',
//     method: 'get'
//   })
// }
export function getRoles() {
  return request({
    url: '/role/getRoleList',
    method: 'get'
  })
}
export function getRoleById(id) {
  return request({
    url: '/role/getRole',
    method: 'get',
    params: {
      id:id
    }
  })
}
// export function addRole(data) {
//   return request({
//     url: '/role',
//     method: 'post',
//     data
//   })
// }
export function addRole(data) {
  return request({
    url: '/role/addRole',
    method: 'post',
    data
  })
}

export function updateRole(id, data) {
  return request({
    url: `/role/updateRole/${id}`,
    method: 'put',
    data
  })
}

export function deleteRole(id) {
  return request({
    url: `/role/deleteRole/${id}`,
    method: 'delete'
  })
}
