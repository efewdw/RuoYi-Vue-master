import request from '@/utils/request'

// 查询用户需求列表
export function listRequirement(query) {
  return request({
    url: '/system/requirement/list',
    method: 'get',
    params: query
  })
}

// 查询用户需求详细
export function getRequirement(id) {
  return request({
    url: '/system/requirement/' + id,
    method: 'get'
  })
}

// 新增用户需求
export function addRequirement(data) {
  return request({
    url: '/system/requirement',
    method: 'post',
    data: data
  })
}

// 修改用户需求
export function updateRequirement(data) {
  return request({
    url: '/system/requirement',
    method: 'put',
    data: data
  })
}

// 删除用户需求
export function delRequirement(id) {
  return request({
    url: '/system/requirement/' + id,
    method: 'delete'
  })
}
