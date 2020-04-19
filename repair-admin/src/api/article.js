import request from '@/utils/request'



export function fetchList(query) {
  return request({
    url: '/info/all',
    method: 'get',
    params: query
  })
}
export function getRepairInfoBySearch(data ) {
    return request({
      url: '/info/search',
      method: 'post',
      data
    })
}

export function fetchArticle(id) {
  return request({
    url: '/article/detail',
    method: 'get',
    params: { id }
  })
}

export function fetchPv(pv) {
  return request({
    url: '/article/pv',
    method: 'get',
    params: { pv }
  })
}

export function createArticle(data) {
  return request({
    url: '/article/create',
    method: 'post',
    data
  })
}
export function updateStateByRepairId(state,repairId) {
  return request({
    url: '/info/updateStateByRepairId',
    method: 'post',
    params: {
      state: state,
      repairId: repairId
    }
  })
}
export function updateRemarkByRepairId(repairId,remark) {
  return request({
    url: '/info/updateRemarkByRepairId',
    method: 'post',
    params: {
      repairId: repairId,
      remark: remark,
    }
  })
}
export function updateArticle(data) {
  return request({
    url: '/update',
    method: 'post',
    data
  })
}
export function addRepairInfo(data) {
  return request({
    url: '/info/addRepairInfo',
    method: 'post',
    data
  })
}
export function updateRepairInfo(data) {
  return request({
    url: '/info/updateRepairInfo',
    method: 'post',
    data
  })
}
export function deleteRepairInfo(repairId) {
  return request({
    url: '/info/deleteRepairInfo',
    method: 'delete',
    params:{
      repairId:repairId
    }
  })
}
