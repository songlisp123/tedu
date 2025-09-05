package com.notice.demo.mapper;

import com.notice.demo.pojo.dto.NoticeListQuery;
import com.notice.demo.pojo.entity.Notice;
import com.notice.demo.pojo.vo.NoticeDetailInfoVO;
import com.notice.demo.pojo.vo.NoticeListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface noticeMapper {

    /**
     * 插入咨询
     * @param notice 具体的咨询对象
     * @return {@code 0} 插入失败<br />
     * {@code other} 插入成功
     */
    int insertNotice(Notice notice);

    /**
     * 从客户端接受的数据{@code noticeListQuery}，并且返回包含<br />
     * {@code NoticeListVO}的{@code list}对象
     * @param noticeListQuery 根据客户端参数而构建的{@code notice}的视图对象
     * @return {@code noticeListQuery}的{@code list}对象，其中<br />
     * {@code NoticeListVO}对象是服务端将要给客户端的数据
     */
    List<NoticeListVO> selectNoticeByTitleAndType(NoticeListQuery noticeListQuery);

    /**
     * 根据id值检索特定的对象
     * @param id 用户将要检索的对象
     * @return {@code NoticeDetailInfoVO}视图对象
     */
    NoticeDetailInfoVO selectNoticeById(Integer id);

    /**
     * 删除特定咨询
     * @param id 咨询的id
     * @return  {@code 0} 删除失败<br />.
     * {@code other} 插入成功
     */
    int deleteNoticeById(Integer id);

    /**
     * 根据id检索咨询条目
     * @param id 将要检索的咨询条目id号码
     * @return {@code notice}对象
     */
    Notice selectById(Integer id);

    /**
     * 更新咨询对象
     * @param notice {@code notice} 对象
     * @return {@code 0} 插入失败<br />
     * {@code other} 插入成功
     */
    int updateNotice(Notice notice);

    /**
     * 选择所有的咨询对象
     * @return 返回一个包含所有咨询的列表对象，否则返回{@code null}
     */
    List<Notice> selectAll();

    /**
     * 按照咨询的类型进行选择
     * @param type 咨询类型
     * @return 包含所有的符合条件的咨询对象的列表，或者返回{@code null}
     */
    List<Notice> selectByType(Integer type);

    /**
     * 从前端接收表示state参数
     * @param state 前端传入的查询路径的参数
     * @return 咨询列表或者返回{@code null}
     */
    List<Notice> selectByStatus(Integer state);
}
