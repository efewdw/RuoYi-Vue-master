import request from '@/utils/request'

// 查询用户（微信用户和企业信息）列表
export function listWxuser(query) {
  return request({
    url: '/system/wxuser/list',
    method: 'get',
    params: query
  })
}

// 查询用户（微信用户和企业信息）详细
export function getWxuser(id) {
  return request({
    url: '/system/wxuser/' + id,
    method: 'get'
  })
}

// 新增用户（微信用户和企业信息）
export function addWxuser(data) {
  return request({
    url: '/system/wxuser',
    method: 'post',
    data: data
  })
}

// 修改用户（微信用户和企业信息）
export function updateWxuser(data) {
  return request({
    url: '/system/wxuser',
    method: 'put',
    data: data
  })
}

// 删除用户（微信用户和企业信息）
export function delWxuser(id) {
  return request({
    url: '/system/wxuser/' + id,
    method: 'delete'
  })
}
