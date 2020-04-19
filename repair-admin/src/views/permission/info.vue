<template>
  <div class="app-container">
    <div class="filter-container">

      <el-input v-model="listQuery.content" placeholder="搜索..." style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <!--<el-select v-model="" placeholder="状态" clearable style="width: 90px" class="filter-item">-->
        <!--<el-option v-for="" :key="item" :label="item" :value="item" />-->
      <!--</el-select>-->
      <!--<el-select v-model="listQuery.type" placeholder="故障类型" clearable class="filter-item" style="width: 130px">-->
        <!--<el-option v-for="item in calendarTypeOptions" :key="item.key" :label="item.display_name+'('+item.key+')'" :value="item.key" />-->
      <!--</el-select>-->
      <!--&lt;!&ndash;clearable 下拉框el-select的是否能够带有取消的X&ndash;&gt;-->
      <!--<el-select v-model="listQuery.sort" placeholder="时间" style="width: 140px" clearable class="filter-item" @change="handleFilter">-->
        <!--<el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key" />-->
      <!--</el-select>-->
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
      </el-button>
      <!--<el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">-->
        <!--添加报修信息-->
      <!--</el-button>-->
      <el-button  type="primary" class="filter-item" @click="handleAddRole" icon="el-icon-edit">添加新角色</el-button>
      <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
        导出
      </el-button>
    </div>

    <el-table :data="list" style="width: 100%;margin-top:10px;" border>
      <el-table-column align="center" label="ID" width="60" >
        <template slot-scope="scope">
          {{ scope.row.userId }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="姓名" width="170">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户名" width="180">
        <template slot-scope="scope">
          {{ scope.row.account }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="角色" max-width="300">
        <template slot-scope="scope" >
          <span v-for="(item, index) in scope.row.roles" :key="index">{{item.roleName + " / " }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户描述" max-width="500">
        <template slot-scope="scope">
          {{ scope.row.description }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="/" width="200">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="handleEdit(scope)">编辑</el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--end-->
    <!--<pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />-->
    <pagination :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="getList" />

    <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?'编辑':'添加'">
      <el-form :model="oList" label-width="80px" label-position="left">
        <el-form-item label="姓名">
          <el-input v-model="oList.name"></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="oList.account" placeholder="用户名" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="oList.password" show-password />
        </el-form-item>
        <el-form-item  label="添加角色">
            <el-select
              multiple
                        filterable
                        allow-create
                        default-first-option v-model="oList.roles" placeholder="角色信息" clearable class="filter-item" style="width: 70%">
              <el-option v-for="(item,index) in rolesList" :key="index" :label="item.roleName" :value="item.roleName" />
            </el-select>
        </el-form-item>

        <el-form-item label="信息详情">
          <el-input
            v-model="oList.description"
            :autosize="{ minRows: 2, maxRows: 4}"
            type="textarea"
            placeholder="角色相关描述"
          />
        </el-form-item>
        <!--<el-form-item label="Menus">-->
          <!--<el-tree-->
            <!--ref="tree"-->
            <!--:check-strictly="checkStrictly"-->
            <!--:data="routesData"-->
            <!--:props="defaultProps"-->
            <!--show-checkbox-->
            <!--node-key="path"-->
            <!--class="permission-tree"-->
          <!--/>-->
        <!--</el-form-item>-->
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" @click="confirmInfo">确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import path from 'path'
import { deepClone } from '@/utils'
import { getRoutes, getRoles, addRole, deleteRole, updateRole } from '@/api/role'
import { getInfo,getInfoAll,searchInfo,deleteInfo,updateInfo,addInfo,searchUserInfo } from '@/api/user'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

// const defaultRole = {
//   key: '',
//   name: '',
//   description: '',
//   routes: []
// }
const defaultList = {
  userId: '',
  name: '',
  account: '',
  password: '',
  roles: '',
  description: '',
}

export default {
  components: { Pagination },
  directives: { waves },
  data() {
    return {
      queryList: {
        title:'',
      },
      listQuery: {
        content:'',
        pageNum: 1,
        pageSize:10,
      },

      list: '',
      total: 0,
      downloadLoading: false,

      oList: Object.assign({}, defaultList),

      roleOptions: [{id:1,role:'admin',},{id:2,role:'tp',}],
      rolesList:[],

      routes: [],
      dialogVisible: false,
      dialogType: 'new',
      checkStrictly: false,
      defaultProps: {
        children: 'children',
        label: 'title'
      }
    }
  },
  computed: {
    routesData() {
      return this.routes
    }
  },
  created() {
    // Mock: get all routes and roles list from server
    // this.getRoutes() // 路由表
    // this.getRoles() // admin editor ...
    this.getList()
    this.getRoles()
  },
  methods: {
    /**
     * 下载
     */
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['timestamp', 'title', 'type', 'importance', 'status']
        const filterVal = ['timestamp', 'title', 'type', 'importance', 'status']
        const data = this.formatJson(filterVal, this.list)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: 'table-list'
        })
        this.downloadLoading = false
      })
    },

    /**
     * 搜索过滤
     * this.listQuery = [content,pageSize,pageNum]
     */
    handleFilter(){
      searchUserInfo(this.listQuery).then(resp => {
        this.list = resp.data.list
        this.total = resp.data.total
        setTimeout(() => {
          this.listLoading = false
        }, 0.5 * 1000)
      })
    },

    /**
     * 获取所有用户
     */
    getList(){
      searchUserInfo(this.listQuery).then(resp => {
        console.log("resp.data")
        console.log(resp.data)
        this.list = resp.data.list
        this.total = resp.data.size// 用total 因为用vo封装了，会报错
      })
    },
    // async getRoutes() {
    //   const res = await getRoutes()
    //   this.serviceRoutes = res.data
    //   console.log(res.data)
    //   this.routes = this.generateRoutes(res.data)
    //
    // },
    async getRoles() {
      const res = await getRoles()
      this.rolesList = res.data
      console.log(this.rolesList)
    },

    // Reshape the routes structure so that it looks the same as the sidebar
    // generateRoutes(routes, basePath = '/') {
    //   const res = []
    //
    //   for (let route of routes) {
    //     // skip some route
    //     if (route.hidden) { continue }
    //
    //     const onlyOneShowingChild = this.onlyOneShowingChild(route.children, route)
    //
    //     if (route.children && onlyOneShowingChild && !route.alwaysShow) {
    //       route = onlyOneShowingChild
    //     }
    //
    //     const data = {
    //       path: path.resolve(basePath, route.path),
    //       title: route.meta && route.meta.title
    //
    //     }
    //
    //     // recursive child routes
    //     if (route.children) {
    //       data.children = this.generateRoutes(route.children, data.path)
    //     }
    //     res.push(data)
    //   }
    //   return res
    // },
    // generateArr(routes) {
    //   let data = []
    //   routes.forEach(route => {
    //     data.push(route)
    //     if (route.children) {
    //       const temp = this.generateArr(route.children)
    //       if (temp.length > 0) {
    //         data = [...data, ...temp]
    //       }
    //     }
    //   })
    //   return data
    // },
    handleAddRole() {
      this.oList = Object.assign({}, defaultList)
      // if (this.$refs.tree) {
      //   this.$refs.tree.setCheckedNodes([])
      // }

      this.dialogType = 'new'
      this.dialogVisible = true
    },

    handleEdit(scope) {
      let roleList =[]
      this.dialogType = 'edit'
      this.dialogVisible = true
      // this.checkStrictly = true
      this.oList = deepClone(scope.row)
      // this.oList = this.list[scope.$index]
      console.log("this.oList.roles")
      // this.oList.roles
      for (let i =0;i<this.oList.roles.length;i++){
        roleList.push(this.oList.roles[i].roleName)
      }
      this.oList.roles = roleList
      console.log(roleList)
      // this.$nextTick(() => {
      //   // const routes = this.generateRoutes(this.oList.routes)
      //   // this.$refs.tree.setCheckedNodes(this.generateArr(routes))
      //   // set checked state of a node not affects its father and child nodes
      //   this.checkStrictly = false
      // })
    },
    handleDelete({ $index, row }) {
      this.$confirm('是否确定要删除', 'Waring', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning'
      })
        .then(async() => {
          deleteInfo (row.id)
          this.list.splice($index, 1)
          this.$message({
            type: 'success',
            message: 'Delete success!'
          })
        })
        .catch(err => { console.error(err) })
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['ID', '姓名', '用户名', '角色', '描述']
        const filterVal = ['userId', 'name', 'account', 'roles', 'description']
        const data = this.formatJson(filterVal, this.list)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: '用户信息'
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        }else if (j === 'roles'){
          let exportRoles = ''
          v[j].forEach( res => {
            exportRoles = exportRoles+res.roleName+"/"
          })
          return exportRoles
        }else {
          return v[j]
        }
      }))
    },

    // generateTree(routes, basePath = '/', checkedKeys) {
    //   const res = []
    //
    //   for (const route of routes) {
    //     const routePath = path.resolve(basePath, route.path)
    //
    //     // recursive child routes
    //     if (route.children) {
    //       route.children = this.generateTree(route.children, routePath, checkedKeys)
    //     }
    //
    //     if (checkedKeys.includes(routePath) || (route.children && route.children.length >= 1)) {
    //       res.push(route)
    //     }
    //   }
    //   return res
    // },



    async confirmInfo() {
      const isEdit = this.dialogType === 'edit'

      if (isEdit) {
        await updateInfo(this.oList.id, this.oList)
        for (let index = 0; index < this.list.length; index++) {
          if (this.list[index].key === this.oList.id) {
            this.list.splice(index, 1, Object.assign({}, this.role))
            break
          }
        }
      }
      else {
        const { data } = await addInfo(this.oList)//需要返回添加的对象（包含id)
        this.oList.id = data.id
        this.list.push(this.oList)
      }

      const { description, userId, name } = this.oList
      this.dialogVisible = false
      this.$notify({
        title: 'Success',
        dangerouslyUseHTMLString: true,
        message: `
            <div>Role Key: ${id}</div>
            <div>Role Name: ${name}</div>
            <div>Description: ${description}</div>
          `,
        type: 'success'
      })
    },
    // reference: src/view/layout/components/Sidebar/SidebarItem.vue
    // onlyOneShowingChild(children = [], parent) {
    //   let onlyOneChild = null
    //   const showingChildren = children.filter(item => !item.hidden)
    //
    //   // When there is only one child route, the child route is displayed by default
    //   if (showingChildren.length === 1) {
    //     onlyOneChild = showingChildren[0]
    //     onlyOneChild.path = path.resolve(parent.path, onlyOneChild.path)
    //     return onlyOneChild
    //   }
    //
    //   // Show parent if there are no child route to display
    //   if (showingChildren.length === 0) {
    //     onlyOneChild = { ... parent, path: '', noShowingChildren: true }
    //     return onlyOneChild
    //   }
    //
    //   return false
    // }
  }
}
</script>

<style lang="scss" scoped>
.app-container {
  .roles-table {
    margin-top: 30px;
  }
  .permission-tree {
    margin-bottom: 30px;
  }
}
</style>
