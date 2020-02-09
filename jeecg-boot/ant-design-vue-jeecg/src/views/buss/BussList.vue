<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :md="6" :sm="8">
            <a-form-item label="运输方式">
              <a-select v-model="queryParam.transportation" placeholder="请选择运输方式">
                <a-select-option value="">请选择运输方式</a-select-option>
                <a-select-option value="A">空运air</a-select-option>
                <a-select-option value="S">海运sea</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>

          <a-col :md="6" :sm="12">
            <a-form-item label="客户姓名">
              <!--<a-input placeholder="请输入客户姓名查询" v-model="queryParam.custName"></a-input>-->
              <j-input placeholder="输入客户姓名模糊查询" v-model="queryParam.custName"></j-input>
            </a-form-item>
          </a-col>

          <a-col :md="6" :sm="8">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator" style="border-top: 5px">
      <a-button @click="handleAdd" type="primary" icon="plus" >添加订单</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('用户信息')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay" @click="handleMenuClick">
          <a-menu-item key="1">
            <a-icon type="delete" @click="batchDel"/>
            删除
          </a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px">
          批量操作
          <a-icon type="down"/>
        </a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i>已选择&nbsp;<a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项&nbsp;&nbsp;
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        bordered
        size="middle"
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <template slot="avatarslot" slot-scope="text, record, index">
          <div class="anty-img-wrap">
            <a-avatar shape="square" :src="getAvatarView(record.avatar)" icon="user"/>
          </div>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical"/>

          <a-dropdown>
            <a class="ant-dropdown-link">
              更多 <a-icon type="down"/>
            </a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a href="javascript:;" @click="handleDetail(record)">详情</a>
              </a-menu-item>

              <a-menu-item>
                <a href="javascript:;" @click="handleChangePassword(record.custName)">密码</a>
              </a-menu-item>

              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>

              <a-menu-item v-if="record.status==1">
                <a-popconfirm title="确定冻结吗?" @confirm="() => handleFrozen(record.id,2,record.custName)">
                  <a>冻结</a>
                </a-popconfirm>
              </a-menu-item>

              <a-menu-item v-if="record.status==2">
                <a-popconfirm title="确定解冻吗?" @confirm="() => handleFrozen(record.id,1,record.custName)">
                  <a>解冻</a>
                </a-popconfirm>
              </a-menu-item>

              <a-menu-item>
                <a href="javascript:;" @click="handleAgentSettings(record.custName)">代理人</a>
              </a-menu-item>

            </a-menu>
          </a-dropdown>
        </span>


      </a-table>
    </div>
    <!-- table区域-end -->

    <bill-modal ref="modalForm" @ok="modalFormOk"></bill-modal>

  </a-card>
</template>

<script>
  import BillModal from './modules/BillModal'
  import {putAction} from '@/api/manage';
  import {frozenBatch} from '@/api/api'
  import {JeecgListMixin} from '@/mixins/JeecgListMixin'
  import JInput from '@/components/jeecg/JInput'

  export default {
    name: "BillList",
    mixins: [JeecgListMixin],
    components: {
      BillModal,
      JInput
    },
    data() {
      return {
        description: '这是用户管理页面',
        queryParam: {},
        columns: [
          /*{
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },*/
          {
            title: '运输方式',
            align: "center",
            dataIndex: 'transportation_dictText',
            width: 120
          },{
            title: '客户姓名',
            align: "center",
            dataIndex: 'custName',
            width: 120
          },
          {
            title: '国家地区',
            align: "center",
            width: 100,
            dataIndex: 'nationalArea_dictText',
          },
          {
            title: '联系人',
            align: "center",
            width: 120,
            dataIndex: 'contacts'
          },

          {
            title: '敏感货',
            align: "center",
            width: 80,
            dataIndex: 'sensitiveGoods_dictText',
            sorter: true
          },
          {
            title: '客户付款时间',
            align: "center",
            width: 180,
            dataIndex: 'custPayDate'
          },
          {
            title: '联系电话',
            align: "center",
            width: 100,
            dataIndex: 'contactsNumber'
          },
          {
            title: '运费',
            align: "center",
            dataIndex: 'totalAmount'
          },
          {
            title: '状态',
            align: "center",
            width: 80,
            dataIndex: 'status_dictText'
          },
         /* {
            title: '创建时间',
            align: "center",
            width: 150,
            dataIndex: 'createTime',
            sorter: true
          },*/
          {
            title: '操作',
            dataIndex: 'action',
            scopedSlots: {customRender: 'action'},
            align: "center",
            width: 170
          }

        ],
        url: {
          imgerver: window._CONFIG['domianURL'] + "/sys/common/view",
          syncUser: "/process/extActProcess/doSyncUser",
          list: "/buss/bill/list",
          delete: "/sys/user/delete",
          deleteBatch: "/sys/user/deleteBatch",
          exportXlsUrl: "/sys/user/exportXls",
          importExcelUrl: "sys/user/importExcel",
        },
      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      getAvatarView: function (avatar) {
        return this.url.imgerver + "/" + avatar;
      },
      handleMenuClick(e) {
        if (e.key == 1) {
          this.batchDel();
        }
      }
    }

  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>