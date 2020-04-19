import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}


export function addInfo(data) {
  return request({
    url:'/user/add',
    method:'post',
    data
  })
}
export function updateInfo(id,data) {
  return request({
    url:`/user/update/${id}`,
    method:'post',
    data
  })
}
export function getInfo() {
  return request({
    url: '/user/info',
    method: 'get',
  })
}
export function getInfoAll() {
  return request({
    url: '/user/info/all',
    method: 'get',
  })
}

export function searchInfo(roles) {
  return request({
    url: '/user/info/search' ,
    method: 'get',
    params: roles
  })
}

export function deleteInfo() {
  return request({
    url: '/user/info/delete',
    method: 'get',
    params:id
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}

export function searchUserInfo(data){
  return request({
    url: '/user/search',
    method: 'post',
    data
  })
}
