<template>
  <div style="margin-left: 100px; width: 50%"  class="block">
    <p>故障树</p>
    <el-tree
      :data="data"
      show-checkbox
      node-key="id"
      default-expand-all
      :expand-on-click-node="false">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
            type="text"
            size="mini"
            @click="() => append(data)">
            <svg-icon icon-class="insert"></svg-icon>
          </el-button>
          <el-button
            type="text"
            size="mini"
            @click="() => remove(node, data)">
            <svg-icon icon-class="delete" ></svg-icon>
          </el-button>
        </span>
      </span>
    </el-tree>
  </div>
  </div>

</template>

<script>
  import Icons from "../icons/index";
  let id = 1000;

  export default {
    components: {Icons},
    data() {
      const data = [{
        id: 1,
        label: 'Wrong Password',
        children: [{
          id: 4,
          label: 'WinPcap问题',
          children: [{
            id: 9,
            label: '需重新安装WinPcap'
          }, {
            id: 10,
            label: 'WinPcap版本过低',
            children: [{
              id: 11,
              label: 'WinPcap残留文件'
            },{
              id: 12,
              label:'网线过长'
            },{
              id: 13,
              label: '没有连接网线或网线接触不良'
            }]
          }]
        }]
      }, {
        id: 2,
        label: '有小蝴蝶账号',
        children: [{
          id: 5,
          label: '有线网连接'
        }, {
          id: 6,
          label: '没有找到有效的物理网卡',
          children:[{
            id:9,
            label:'小蝴蝶没有设置网卡'
          }]
        }]
      }, {
        id: 3,
        label: '一级 3',
        children: [{
          id: 7,
          label: '二级 3-1'
        }, {
          id: 8,
          label: '二级 3-2'
        }]
      }];
      return {
        data: JSON.parse(JSON.stringify(data)),
        data: JSON.parse(JSON.stringify(data))
      }
    },

    methods: {
      append(data) {
        const newChild = { id: id++, label: 'testtest', children: [] };
        if (!data.children) {
          this.$set(data, 'children', []);
        }
        data.children.push(newChild);
      },

      remove(node, data) {
        const parent = node.parent;
        const children = parent.data.children || parent.data;
        const index = children.findIndex(d => d.id === data.id);
        children.splice(index, 1);
      },

      renderContent(h, { node, data, store }) {
        return (
          <span class="custom-tree-node">
          <span>{node.label}</span>
          <span>
          <el-button size="mini" type="text" on-click={ () => this.append(data) }>Append</el-button>
        <el-button size="mini" type="text" on-click={ () => this.remove(node, data) }>Delete</el-button>
        </span>
        </span>);
      }
    }
  };
</script>
<style>
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }
</style>

