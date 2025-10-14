<template>
    <div style="height: 6vh;background-color:#fff;padding:10px 20px;">
        <span style="line-height:60px;font-size:20px;">申请单列表</span>
        <el-button type="primary" style="float:right;margin-top:13px;"
         @click="dialogVisible=true">申请用车</el-button>
    </div>
    <!-- 搜索表单 -->
    <el-card style="margin: 10px;">
        <el-form style="padding-top: 10px;">
            <el-row :gutter="30">
                <el-col :span="5">
                    <el-form-item label="出发地">
                        <el-input placeholder="请输入数据……" ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item label="终止地">
                        <el-input placeholder="请输入数据……" ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item label="申请单状态">
                        <el-select placeholder="----请选择----">
                            <el-option value="10" label="已发起"></el-option>
                            <el-option value="20" label="撤销"></el-option>
                            <el-option value="30" label="审核中"></el-option>
                            <el-option value="40" label="驳回"></el-option>
                            <el-option value="50" label="已通过"></el-option>
                            <el-option value="60" label="分配用车"></el-option>
                            <el-option value="70" label="工单结束"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="5">
                    <el-form-item>
                        <el-button type="info">重置</el-button>
                        <el-button type="primary">确认</el-button>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
    </el-card>

    <!-- 申请单列表 -->
    <el-card style="margin: 10px;padding: 10px;">
        <el-table>
            <el-table-column type="index" label="编号"  prop="id"
            width="80" align="center"></el-table-column>
            <el-table-column label="申请人" prop="username" align="center"></el-table-column>
            <el-table-column label="出发地" prop="departureAddr" align="center"></el-table-column>
            <el-table-column label="目的地" prop="destinationAddr" align="center"></el-table-column>
            <el-table-column label="用车原因" prop="reason" align="center"></el-table-column>
            <el-table-column label="审批人" prop="auditUsernameList" align="center"></el-table-column>
            <el-table-column label="用处开始时间" prop="startTime" align="center"></el-table-column>
            <el-table-column label="结束时间" prop="endTime" align="center"></el-table-column>
            <el-table-column label="申请单状态" prop="status" align="center"></el-table-column>
            <el-table-column label="操作" align="center">
                <template #default="scope">
                    <el-button type="primary" size="small" link>撤销</el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-card>

    <!-- 弹窗警告 -->
    <el-dialog
    title="新建申请单"
    style="width: 1000px;padding: 40px;"
    v-model="dialogVisible"
    >
        <el-form label-width="80px" label-position="top">
            <el-row :gutter="30">
                <el-col :span="12">
                    <el-form-term label="用车人">
                        <el-input placeholder="请输入" readonly></el-input>
                    </el-form-term>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="使用时间">
                        <el-date-picker
                            type="datetimerange"
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            value-format="YYYY-MM-DD HH:mm:ss"
                            format="YYYY-MM-DD HH:mm:ss"
                        ></el-date-picker>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="30">
                <el-col :span="12">
                    <el-form-item label="车辆出发地">
                        <el-input placeholder="请输入……"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="车辆目的地">
                        <el-input placeholder="请输入……"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="30">
                <el-col :span="12">
                    <el-form-item label="用车事由">
                        <el-input placeholder="请输入……" type="textarea" 
                        :rows="3" resize="none"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="备注">
                        <el-input placeholder="请输入……" type="textarea" 
                        :rows="3" resize="none"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="30">
                <el-col :span="12">
                    <el-form-item label="驾照">
                        <el-upload
                            v-model:file-list="fileList"
                            v-model:action="actionUrl"
                            name="file"
                            limit="1"
                            list-type="picture-card"
                            :on-preview="handlePictureCardPreview"
                            :on-remove="handleRemove"
                            >
                            <el-icon><Plus/></el-icon>
                        </el-upload>
                        <el-dialog>
                            <img w-full>
                        </el-dialog>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="审批人">
                        <el-row :gutter="10">
                            <el-col :span="12">
                                <el-tag type="primary">
                                    <el-icon><User /></el-icon>
                                    <span>审批人姓名</span>
                                </el-tag>
                            </el-col>
                            <el-col :span="12">
                                <el-tag type="primary">
                                    <el-icon><User /></el-icon>
                                    <span>审批人姓名</span>
                                </el-tag>
                            </el-col>
                        </el-row>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <template #footer>
            <el-button type="info">取消</el-button>
            <el-button type="primary">确定</el-button>
        </template>
    </el-dialog>

</template>

<script setup>
import { ref } from 'vue';

//定义弹窗的出现关闭
const dialogVisible = ref(false);
</script>

<style></style>