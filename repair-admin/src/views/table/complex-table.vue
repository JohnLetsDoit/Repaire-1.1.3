<template>
  <div class="app-container">
    <div class="filter-container">
      <!--输入要搜索的任何内容-->
      <!--回车进行确定-->
      <el-input v-model="listQuery.content" placeholder="搜索..." style="width: 300px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search"  @click="handleFilter"></el-button>

      <!--搜索当前维修状态为0（未完成）或1（已完成）的信息value为0，1-->
      <el-select  @change="handleFilter" v-model="listQuery.state" placeholder="状态" clearable style="width: 90px;margin-left: 70px;" class="filter-item">
        <el-option v-for="(item,index) in stateList" :key="index" :label="item.title" :value="item.code" />
      </el-select>
      <!--<el-select  :value="valueTitle" :clearable="clearable" class="filter-item" style="width: 220px">-->
        <!--<el-option :value="valueTitle" :label="valueTitle" >-->
          <!--<el-tree id="tree-option"-->
            <!--ref="selectTree"-->
            <!--:accordion="accordion"-->
            <!--:props="props"-->
            <!--:node-key="props.value"-->
            <!--:data="options"-->
            <!--:default-expanded-keys="defaultExpandedKey"-->
            <!--@node-click="handleNodeClick">-->
          <!--</el-tree>-->
        <!--</el-option>-->
      <!--</el-select>-->
      <!--clearable 下拉框el-select的是否能够带有取消的X-->
      <!--选择预约的时间，orderTimeList是 当前日期（yyyy-mm-dd) + 固定格式（星期x 第 x 单元）-->
      <!--<el-select v-model="listQuery.orderTime" placeholder="预约时间" style="width: 140px" clearable class="filter-item" @change="handleFilter">-->
        <el-cascader style="margin-left: 20px" @change="handleFilter" clearable class="filter-item"
                     placeholder="选择预约时间"
          :options="orderTimeList"
          v-model="orderTimeArr"
          >
        </el-cascader>

        <!--<el-option v-for="(item,index) in orderTimeList" :key="item.index" :label="item" :value="item" />-->
      <!--</el-select>-->
      <!-- 搜索按钮，执行handleFilter()方法-->
      <!--<el-button v-waves class="filter-item" type="primary" icon="el-icon-search"  @click="handleFilter"></el-button>-->
      <el-button class="filter-item" style="margin-left: 100px;" type="danger" icon="el-icon-edit" @click="handleCreate">
        添加报修信息
      </el-button>
      <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
        导出
      </el-button>

      <!--搜索行下一行，控制列表行列的默认显示-->
      <div class="filter-container">
        <el-checkbox v-model="IDView" class="filter-item" style="margin-left:5px;" @change="tableKey=tableKey+1">
          ID
        </el-checkbox>
        <el-checkbox v-model="serviceStateView" class="filter-item" style="margin-left:5px;" @change="tableKey=tableKey+1">
          维修状态
        </el-checkbox>
        <el-checkbox v-model="faultInfoView" class="filter-item" style="margin-left:5px;" @change="tableKey=tableKey+1">
          故障信息
        </el-checkbox>
        <el-checkbox v-model="repairInfoView" class="filter-item" style="margin-left:5px;" @change="tableKey=tableKey+1">
          报修人信息
        </el-checkbox>
        <el-checkbox v-model="recordInfoView" class="filter-item" style="margin-left:5px;" @change="tableKey=tableKey+1">
          录入信息
        </el-checkbox>
        <el-checkbox v-model="serviceInfoView" class="filter-item" style="margin-left:5px;" @change="tableKey=tableKey+1">
          维修信息
        </el-checkbox>
      </div>
  </div>

<!--table 表单-->
    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      :row-class-name="tableRowClassName"
      style="width: 100%;"
      @sort-change="sortChange"
    >
      <el-table-column v-if="IDView" label="ID" prop="id"  align="center" width="50">
        <template slot-scope="scope">
          <span>{{ scope.row.repairId }}</span>
        </template>
      </el-table-column>

      <el-table-column v-if="serviceStateView" label="状态" prop="state"  align="center" width="100">
        <template slot-scope="scope">
          <el-tag style="color: white;background: #1890ff;width: 95%;height: 95%" size="large" v-if="scope.row.state == 2">已完成</el-tag>
          <el-tag style="color: white;background: red;width: 95%;height: 95%" size="large" type="danger" v-else>未完成</el-tag>
        </template>
      </el-table-column>
      <!--<el-table-column v-if="faultInfoView" label="故障信息" align="center">-->
        <!--<el-table-column label="故障类型" prop="faultName" align="center" max-width="200">-->
          <!--<template slot-scope="scope">-->
            <!--<span>{{ scope.row.faultName }}</span>-->
          <!--</template>-->
        <!--</el-table-column>-->
        <el-table-column  label="故障信息" prop="faultName" align="center" min-width="200">
          <template slot-scope="scope">
            <span style="font-family:'PingFang SC'">{{ scope.row.faultDesc }}</span>
          </template>
        </el-table-column>
      <!--</el-table-column>-->

      <el-table-column v-if="repairInfoView" label="报修人信息" align="center">
        <el-table-column  label="姓名" prop="faultName" align="center" max-width="80">
          <template slot-scope="scope">
            <span>{{ scope.row.repairName }}</span>
          </template>
        </el-table-column>
        <el-table-column  label="学号(工号)" prop="faultName" align="center" width="100">
          <template slot-scope="scope">
            <span>{{ scope.row.number }}</span>
          </template>
        </el-table-column>
        <el-table-column  label="地址" prop="faultName" align="center" width="120">
          <template slot-scope="scope">
            <span>{{ scope.row.repairAddress }}{{scope.row.repairRoom}}</span>
          </template>
        </el-table-column>
        <el-table-column show-overflow-tooltip label="预约时间" prop="faultName" align="center" width="100">
          <template slot-scope="scope">
            <span>{{ scope.row.orderTime }}</span>
          </template>
        </el-table-column>
        <el-table-column label="电话" prop="faultName" align="center" width="120">
          <template slot-scope="scope">
            <span>{{ scope.row.phone }}</span>
          </template>
        </el-table-column>
        <el-table-column show-overflow-tooltip label="报修备注"  align="center" min-width="200">
          <template slot-scope="scope">
            <span>{{ scope.row.description }}</span>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column  v-if="recordInfoView" label="录入信息" align="center">
        <el-table-column  label="录入人" prop="faultName" align="center" width="100">
          <template slot-scope="scope">
            <span  v-if="scope.row.recordUser !== null">{{ scope.row.recordUser.name }}</span>
            <!--<span v-else ></span>-->
          </template>
        </el-table-column>
        <el-table-column label="录入时间" prop="faultName" align="center" width="170">
          <template slot-scope="scope">
            <span v-if="scope.row.createTime !== null">{{ scope.row.createTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column  v-if="serviceInfoView" label="维修信息" align="center">
        <el-table-column  label="维修人"  align="center" width="100">
          <template slot-scope="scope">
            <span  v-if="scope.row.serviceUser !== null">{{ scope.row.serviceUser.name }}</span>
          </template>
        </el-table-column>
        <el-table-column  label="维修时间"  align="center" width="170">
          <template slot-scope="scope">
            <span v-if="scope.row.repairTime !== null">{{ scope.row.repairTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
        <el-table-column  show-overflow-tooltip label="维修备注"  align="center" max-width="300">
          <template slot-scope="scope">
            <span>{{ scope.row.remark }}</span>
          </template>
        </el-table-column>
      </el-table-column>
      <!--这一部分需要控制角色认定判断，-->
      <el-table-column label="操作" align="center"
                       v-if="checkPermission(['admin'])?handleWidth=250:handleWidth=150"
                       :width="handleWidth"
                       class-name="small-padding fixed-width">
        <template  slot-scope="{row}">
          <el-button  v-if="checkPermission(['admin','record'])" type="primary" size="mini" @click="handleUpdate(row)">
            <svg-icon icon-class="edit"></svg-icon>编辑
          </el-button>
          <el-button size="mini" disabled v-if="row.state == 2" type="info" >
            <svg-icon icon-class="drag"></svg-icon>已完成
          </el-button>
          <el-button size="mini" v-else type="success" @click="handleModifyStatus(row,'accept')">
            <svg-icon icon-class="drag"></svg-icon>完成
          </el-button>
          <el-button  v-if="checkPermission(['admin'])" size="mini" type="danger" @click="handleModifyStatus(row,'delete')">
            <svg-icon icon-class="delete"></svg-icon>
            <span style="color: white;font-family: 'Microsoft YaHei';">删除</span>
          </el-button>
        </template>
      </el-table-column>
    </el-table>
<!-- table_end -->
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="getList" />
    <!--这是编辑界面弹出框-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="90px" style="width: 550px; margin-left:50px;">
        <!--<el-form-item label="故障类型" prop="faultName">-->
          <!--<el-select v-model="temp.faultId" class="filter-item" placeholder="Please select">-->
            <!--<el-option v-for="item in repairAddress" :key="item" :label="item" :value="item" />-->
          <!--</el-select>-->
        <!--</el-form-item>-->
        <el-form-item  label="故障描述" prop="faultDesc">
          <el-input v-model="temp.faultDesc" :autosize="{ minRows: 2, maxRows: 4}"  type="textarea"/>
        </el-form-item>
        <el-form-item label="报修人姓名" prop="stuName">
          <el-input v-model="temp.repairName" />
        </el-form-item>
        <el-form-item label="学号(工号)" prop="stuNum">
          <el-input v-model="temp.number" />
        </el-form-item>
        <el-form-item label="公寓楼" prop="roomNum">
          <el-select style="width: 200px"  v-model="temp.repairAddress" class="filter-item" placeholder="选择公寓楼">
            <el-option v-for="item in repairAddress " :key="item" :label="item" :value="item"/>
          </el-select>
          <span style="font-weight: bold; width: 70px;margin-left: 40px;margin-right: 20px" >房间</span>
          <el-input label="房间号" style="width: 160px" v-model="temp.repairRoom" />
        </el-form-item>

        <el-form-item label="预约时间" >
          <!--<span style="font-weight: bold;margin-right: 30px;" >预约时间</span>-->
          <el-cascader
            style="width: 300px"
            class="filter-item"
                       :options="orderTimeList"
                       v-model="orderTimeArr"
          >
          </el-cascader>
        </el-form-item>

        <el-form-item label="电话" prop="phone">
          <el-input v-model="temp.phone" />
        </el-form-item>
        <el-form-item label="备注" prop="faultDesc">
          <el-input v-model="temp.description" :autosize="{ minRows: 2, maxRows: 4}"  type="textarea"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          确定
        </el-button>
      </div>
    </el-dialog>



    <!--操作备注-->
    <el-dialog :visible.sync="dialogPvVisible" title="操作备注">
      <el-form  ref="remarkForm" :model="temp" border fit highlight-current-row style="width: 100%">
        <el-form-item prop="pv" left label="操作备注" >
          <el-input v-model="temp.remark" :autosize="{ minRows: 2, maxRows: 4}"  type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="HandleRemark"> 确认</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<style>
  .el-table .warning-row {
    background: #fde2e2;
  }
  .el-table tbody tr:hover>td { background-color: #ffffff !important; }
  .el-table .success-row {
    background: #f5f7fa;
  }
  .el-table_1_column_5 {
    background: #1890ff;
  }
  .el-table thead.is-group th {
    color: darkslateblue !important;
  }

</style>

<script>
import { updateRepairInfo,addRepairInfo,deleteRepairInfo,fetchList, fetchPv, createArticle, updateArticle, updateStateByRepairId, updateRemarkByRepairId,getRepairInfoBySearch } from '@/api/article'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import checkPermission from '@/utils/permission'


export default {
  name: 'index',
  components: { Pagination },
  directives: { waves },
  /**
   * filters过滤器
   * 0:success
   * 1:info
   * 2:danger
   */
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    typeFilter(type) {
      return calendarTypeKeyValue[type]
    }
  },
  /**
   * 主要用来插入下拉表中插入故障树
   */
  props:{
    /* 配置项 */
    props:{
      type: Object,
      default:()=>{
        return {
          value:'id',             // ID字段名
          label: 'title',         // 显示名称
          children: 'children'    // 子级字段名
        }
      }
    },
    /* 选项列表数据(树形结构的对象数组) */
    faultList:{
      type: Array,
      default: ()=> {
        return [{id: 1, parentId: 0, name: "一级菜单A", rank: 1},
          {id: 2, parentId: 0, name: "一级菜单B", rank: 1},
          {id: 3, parentId: 0, name: "一级菜单C", rank: 1},
          {id: 4, parentId: 1, name: "二级菜单A-A", rank: 2},
          {id: 5, parentId: 1, name: "二级菜单A-B", rank: 2},
          {id: 6, parentId: 2, name: "二级菜单B-A", rank: 2},
          {id: 7, parentId: 4, name: "三级菜单A-A-A", rank: 3},
          {id: 8, parentId: 7, name: "四级菜单A-A-A-A", rank: 4},
          {id: 9, parentId: 8, name: "五级菜单A-A-A-A-A", rank: 5},
          {id: 10, parentId: 9, name: "六级菜单A-A-A-A-A-A", rank: 6},
          {id: 11, parentId: 10, name: "七级菜单A-A-A-A-A-A-A", rank: 7},
          {id: 12, parentId: 11, name: "八级菜单A-A-A-A-A-A-A-A", rank: 8},
          {id: 13, parentId: 12, name: "九级菜单A-A-A-A-A-A-A-A-A", rank: 9},
          {id: 14, parentId: 13, name: "十级菜单A-A-A-A-A-A-A-A-A-A", rank: 10}]
      }
    },
    /* 初始值 */
    value:{
      type: Number,
      default: ()=>{ return null }
    },
    /* 可清空选项 */
    clearable:{
      type:Boolean,
      default:()=>{ return true }
    },
    /* 自动收起 */
    accordion:{
      type:Boolean,
      default:()=>{ return false }
    },
  },
  /**
   * 以上是为了选择故障信息，下拉框插入故障节点
   * 以下是data，所有与故障有关的用 'FAULT_SIGN'
   */
  data() {
    return {
      key: 1,
      //FAULT_SIGN
      valueId:this.value,    // 初始值
      valueTitle:'',
      defaultExpandedKey:[],

      handleWidth:250,
      //完成，disabled显示
      finishWork: true,
      //不知道干嘛的，用来控制列表行列显示的
      tableKey: 0,
      //信息列表
      list: '',
      //信息总数
      total: 0,
      orderTimeArr: undefined,
      //查询列表信息需要的参数
      listQuery: {
        content: undefined,
        faultId: undefined,
        orderTime:undefined,
        pageNum:1,
        pageSize:10,
        state:undefined
      },

      /* 搜索下拉，状态的选择 */
      stateList: [
        {title: '已完成',code: 2},
        {title: '未完成',code: 1}
      ],
      /* 预约时间下拉，选择预约时间 */
      orderTimeList: [
        {
          value: parseTime(new Date(),'{y}-{m}-{d}/周{a}'),
          label: parseTime(new Date(),'{y}-{m}-{d}/周{a}'),
            children:[{
              value: '一单元',
              label: '一单元'
            }, {
              value: '二单元',
              label: '二单元'
            }, {
              value: '三单元',
              label: '三单元'
            }, {
              value: '四单元',
              label: '四单元'
            }]
          },
        {
          value: parseTime(new Date().setDate(new Date().getDate()-1),'{y}-{m}-{d}/周{a}'),
          label: parseTime(new Date().setDate(new Date().getDate()-1),'{y}-{m}-{d}/周{a}'),
            children: [{
              value: '一单元',
              label: '一单元'
            }, {
              value: '二单元',
              label: '二单元'
            }, {
              value: '三单元',
              label: '三单元'
            }, {
              value: '四单元',
              label: '四单元'
            }]
          },
        {
          value: parseTime(new Date().setDate(new Date().getDate()-2),'{y}-{m}-{d}/周{a}'),
          label: parseTime(new Date().setDate(new Date().getDate()-2),'{y}-{m}-{d}/周{a}'),
            children: [{
              value: '一单元',
              label: '一单元'
            }, {
              value: '二单元',
              label: '二单元'
            }, {
              value: '三单元',
              label: '三单元'
            }, {
              value: '四单元',
              label: '四单元'
            }]
          },

          ],


    orderTimeListValue:[],
      /* 公寓楼 */
      repairAddress: ['南三公寓','北三公寓'],

      // Loading框
      downloadLoading: false,
      listLoading: true,

      /*控制列表行列的显示*/
      repairInfoView: true,
      recordInfoView: false,
      serviceInfoView:false,
      faultInfoView: true,
      serviceStateView: true,//维修状态，已完成，未完成
      IDView: false,


      //这是弹出框的标题，是E
      temp: {
        repairId: undefined,
        faultId: undefined,
        faultDesc: undefined,
        description: undefined,
        //student
        number:undefined,
        repairName:undefined,
        repairAddress:undefined,
        repairRoom: undefined,
        orderTime:undefined,
        remark: '',
        recordUserId: undefined,
        serviceUserId: undefined,
      },
      //是否显示修改弹出框
      dialogFormVisible: false,
      //弹出值的状态是否未create
      dialogStatus: '',
      //确定是Edit 还是Create,等有时间我把添加分出来一个框就没这么多事了
      textMap: {
        update: '编辑报修信息',
        create: '添加报修信息'
      },
      //用于操作点击完成时候，弹出填写操作备注
      dialogPvVisible: false,
      pvData: [],

      //所有输入验证规则
      rules: {
        type: [{ required: true, message: 'type is required', trigger: 'change' }],
        timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
        title: [{ required: true, message: 'title is required', trigger: 'blur' }]
      },

    }
  },
  /**
   * created
   * 在实例创建完成后被立即调用
   */
  created() {
    this.getList()
    // this.orderTimeListGroup()
  },
  //FAULT_SIGN
  mounted(){
    this.initHandle()
  },
  //FAULT_SIGN
  watch: {
    value(){
      this.valueId = this.value
      this.initHandle()
    }
  },
  //FAULT_SIGN
  computed:{
    treeData(){
      let cloneData = JSON.parse(JSON.stringify(this.options))    // 对源数据深度克隆
      return cloneData.filter(father=>{
        let branchArr = cloneData.filter(child=>father.id == child.parentId)    //返回每一项的子级数组
        branchArr.length>0 ? father.children = branchArr : ''   //如果存在子级，则给父级添加一个children属性，并赋值
        return father.parentId==0;      //返回第一层
      });
    }
  },

  methods: {
    checkPermission,
    //FAULT_SIGN
    initHandle(){
      if(this.valueId){
        this.valueTitle = this.$refs.selectTree.getNode(this.valueId).data[this.props.label]     // 初始化显示
        this.$refs.selectTree.setCurrentKey(this.valueId)       // 设置默认选中
        this.defaultExpandedKey = [this.valueId]      // 设置默认展开
      }
      this.$nextTick(()=>{
        let scrollWrap = document.querySelectorAll('.el-scrollbar .el-select-dropdown__wrap')[0]
        let scrollBar = document.querySelectorAll('.el-scrollbar .el-scrollbar__bar')
        scrollWrap.style.cssText = 'margin: 0px; max-height: none; overflow: hidden;'
        scrollBar.forEach(ele => ele.style.width = 0)
      })

    },


    isHaveRole(roleName) {
      let roleNames =[]
      this.$store.getters.roles.forEach( res => {
        roleNames.push(res.roleName)
      })
      if (roleNames.includes(roleName)){
        return true
      } else {
        return false
      }
    },
    /**
     * 获取列表信息通过 fetchList()方法，参数为listQuery
     *
     */

    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
        setTimeout(() => {
          this.listLoading = false
        }, 0.5 * 1000)
      })
    },
    /**
     * 用于搜索使用
     */
    handleFilter() {
      this.listLoading = true
      // let orderTime =''
      // console.log(this.orderTimeArr)
      // for (let i=0;i<this.orderTimeArr.length;i++){
      //   orderTime += this.orderTimeArr[i]
      // }
      // console.log(orderTime)
      this.listQuery.orderTime = this.orderTimeArr.join(" ")
      getRepairInfoBySearch(this.listQuery).then( response => {
        this.list = response.data.list
        this.total = response.data.total
        setTimeout(() => {
          this.listLoading = false
        }, 0.5 * 1000)
      })
    },
    /**
     * 点击操作完成和删除时候使用
     */
    handleModifyStatus(row, status) {
      // const name = this.store.getters.name
      if(status == 'accept'){
        this.dialogPvVisible = true
        this.temp = Object.assign({},row)
        row.state = 2
        updateStateByRepairId(row.state,row.repairId).then(this.getList)
      }else if (status == 'delete'){
        deleteRepairInfo(row.repairId).then(this.getList)
        this.$notify({
          title: '成功',
          message: '删除',
          type: 'success',
          duration: 2000
        })
      }

      row.status = status

    },

    sortChange(data) {
      const { prop, order } = data
      if (prop === 'id') {
        this.sortByID(order)
      }
    },
    sortByID(order) {
      if (order === 'ascending') {
        this.listQuery.sort = '+id'
      } else {
        this.listQuery.sort = '-id'
      }
      this.handleFilter()
    },
    //重置Temp,添加信息的时候用，置空
    resetTemp() {
      this.orderTimeArr = undefined,
      this.temp = {
        repairId: undefined,
        faultId: undefined,
        faultDesc: undefined,
        description: undefined,
        //student
        number:undefined,
        repairName:undefined,
        repairAddress:undefined,
        repairRoom: undefined,
        orderTime:undefined,
        remark: '',
        recordUserId: undefined,
        serviceUserId: undefined
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.temp.recordUserId = this.$store.getters.userId
          // let orderTime =''
          // console.log("his.orderTimeArr")
          // console.log(this.orderTimeArr)
          // for (let i=0;i<this.orderTimeArr.length;i++){
          //   orderTime += this.orderTimeArr[i]
          // }
          //
          // console.log(this.orderTimeArr.join(" "))

          this.temp.orderTime = this.orderTimeArr.join(" ")
          addRepairInfo(this.temp).then(() => {
            this.dialogFormVisible =false
            this.getList()
            this.$notify({
              title: '成功',
              message: '添加成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    HandleRemark(){
      this.dialogPvVisible = false
      this.$refs['remarkForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          console.log("tempData")
          console.log(tempData)
          tempData.serviceUserId = this.$store.getters.userId
          tempData.state = 2
          // addRemarkByRepairId(tempData.repairId,tempData.remark).then(this.getList)
          updateRepairInfo(tempData).then(this.getList)
        }
      })
      // this.dialogPvVisible = false
      // updateRemarkByRepairId(this.temp.repairId,this.temp.remark).then(this.getList)
      this.$message({
        message: '操作Success',
        type: 'success'
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      let orderTimeArray = []
      console.log("this.temp.orderTime")
      console.log(this.temp)
      if (this.temp.orderTime !== '' && this.temp.orderTime !== null){
        orderTimeArray = this.temp.orderTime.split(" ")
      }
      this.orderTimeArr= orderTimeArray
      // console.log(this.orderTimeArr)
      // this.temp.timestamp = new Date(this.temp.timestamp)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      //移除校验
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          tempData.orderTime = this.orderTimeArr.join(" ")
          // tempData.timestamp = +new Date(tempData.timestamp) // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
          //timetamp 不能删除，传给后端去修改
          //timeData 就是传给后端的所有要修改的数据了
          updateRepairInfo(tempData).then(() => {
            // for (const v of this.list) {
            //   if (v.id === this.temp.id) {
            //     const index = this.list.indexOf(v)
            //     this.list.splice(index, 1, this.temp)
            //     break
            //   }
            // }
            this.getList()
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Update Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleDelete(row) {
      this.$notify({
        title: '成功',
        message: '删除成功',
        type: 'success',
        duration: 2000
      })
      const index = this.list.indexOf(row)
      this.list.splice(index, 1)
    },
    handleFetchPv(pv) {
      fetchPv(pv).then(response => {
        this.pvData = response.data.pvData
        this.dialogPvVisible = true
      })
    },
    /**
     * 导出用的，如果需要做一些逻辑运算，在formatJson进行格式转换
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
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    },


    // 切换选项
    //FAULT_SIGN
    handleNodeClick(node){
      this.valueTitle = node[this.props.label]
      this.valueId = node[this.props.value]
      this.$emit('getValue',this.valueId)
      this.defaultExpandedKey = []
      this.treeData()
    },
    // 清除选中
    //FAULT_SIGN
    clearHandle(){
      this.valueTitle = ''
      this.valueId = null
      this.defaultExpandedKey = []
      this.clearSelected()
      this.$emit('getValue',null)
    },

    tableRowClassName({row, rowIndex}) {
      if (row.state != 2) {
        return 'warning-row';
      } else if (rowIndex%2 == 1) {
        return 'success-row';
      }
      return '';
    }
    }
}
</script>

<style scoped>
  .el-table thead.is-group th {
    color: darkslateblue !important;
  }
  /*.el-scrollbar .el-scrollbar__view .el-select-dropdown__item{*/
    /*height: auto;*/
    /*max-height: 274px;*/
    /*padding: 0;*/
    /*overflow: hidden;*/
    /*overflow-y: auto;*/
  /*}*/
  /*.el-select-dropdown__item.selected{*/
    /*font-weight: normal;*/
  /*}*/
  /*ul li >>>.el-tree .el-tree-node__content{*/
    /*height:auto;*/
    /*padding: 0 20px;*/
  /*}*/
  /*.el-tree-node__label{*/
    /*font-weight: normal;*/
  /*}*/
  /*.el-tree >>>.is-current .el-tree-node__label{*/
    /*color: #409EFF;*/
    /*font-weight: 700;*/
  /*}*/
  /*.el-tree >>>.is-current .el-tree-node__children .el-tree-node__label{*/
    /*color:#606266;*/
    /*font-weight: normal;*/
  /*}*/

 </style>
