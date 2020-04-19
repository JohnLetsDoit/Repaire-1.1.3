import store from '@/store'

/**
 * role: {roleId,roleName,description}
 * 注意⚠️: 因为判断的是roleName,所以 permissionRoles.includes(role.roleName)
 * @param {Array} value
 * @returns {Boolean}
 * @example see @/views/permission/directive.vue
 */
export default function checkPermission(value) {
  if (value && value instanceof Array && value.length > 0) {
    const roles = store.getters && store.getters.roles
    const permissionRoles = value
    const hasPermission = roles.some(role => {
      return permissionRoles.includes(role.roleName) // role: {roleId,roleName,description}
    })

    if (!hasPermission) {
      return false
    }
    return true
  } else {
    console.error(`需要传入一个角色`)
    return false
  }
}

