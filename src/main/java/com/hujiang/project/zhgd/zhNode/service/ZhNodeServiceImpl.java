package com.hujiang.project.zhgd.zhNode.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hujiang.project.zhgd.zhNode.mapper.ZhNodeMapper;
import com.hujiang.project.zhgd.zhNode.domain.ZhNode;
import com.hujiang.project.zhgd.zhNode.service.IZhNodeService;
import com.hujiang.common.support.Convert;
import org.springframework.web.multipart.MultipartFile;

/**
 * 节点计划详情 服务层实现
 * 
 * @author hujiang
 * @date 2019-08-01
 */
@Service
public class ZhNodeServiceImpl implements IZhNodeService 
{
	@Autowired
	private ZhNodeMapper zhNodeMapper;

	/**
     * 查询节点计划详情信息
     * 
     * @param id 节点计划详情ID
     * @return 节点计划详情信息
     */
    @Override
	public ZhNode selectZhNodeById(Integer id)
	{
	    return zhNodeMapper.selectZhNodeById(id);
	}

	@Override
	public ZhNode selectZhNodeByProgressId(Integer progressId) {
		return zhNodeMapper.selectZhNodeByProgressId(progressId);
	}

	/**
     * 查询节点计划详情列表
     * 
     * @param zhNode 节点计划详情信息
     * @return 节点计划详情集合
     */
	@Override
	public List<ZhNode> selectZhNodeList(ZhNode zhNode)
	{
	    return zhNodeMapper.selectZhNodeList(zhNode);
	}
	
    /**
     * 新增节点计划详情
     * 
     * @param zhNode 节点计划详情信息
     * @return 结果
     */
	@Override
	public int insertZhNode(ZhNode zhNode)
	{
	    return zhNodeMapper.insertZhNode(zhNode);
	}
	
	/**
     * 修改节点计划详情
     * 
     * @param zhNode 节点计划详情信息
     * @return 结果
     */
	@Override
	public int updateZhNode(ZhNode zhNode)
	{
	    return zhNodeMapper.updateZhNode(zhNode);
	}


	/**
	 * 删除节点计划详情对象
	 *
	 * @param id 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteZhNodeById(int id) {
		return zhNodeMapper.deleteZhNodeById(id);
	}




/*public boolean ImportEexlService(MultipartFile file){
		try {
			//解析xlsx后缀的excel文件 xls后缀的暂时没写
			readXlsx(file.getInputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public void readXlsx(InputStream inputStream ) throws IOException {

		//XSSFWorkbook  需要一个inputStream流 在上面我们已经把file转成了流所以现在直接用就可以了
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		String comcode=null;
		String comname=null;
		String ck_rank=null;
		String ck_score=null;
		String fw_rank=null;
		String fw_score=null;
		//获取第一个Sheet 遍历获取每一行数据
		for(int rowNum = 2; rowNum <= xssfSheet.getLastRowNum(); rowNum++ ){
			//从第二行开始遍历 
			//取每一行每一列的值
			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
			//取第一行第一个格的值
			comcode=getValue(xssfRow.getCell(0));
			comname=getValue(xssfRow.getCell(1));
			ck_rank=getValue(xssfRow.getCell(2));
			ck_score=getValue(xssfRow.getCell(3));
			fw_rank=getValue(xssfRow.getCell(4));
			fw_score=getValue(xssfRow.getCell(5));
			//把获取到的数据直接插入到数据库
			String sql=" insert　into　t_car_service(id,comcode,comname,ck_rank,ck_score,fw_rank,fw_score,create_date) "  + " values('"+ UUID.randomUUID().toString()+"','"+comcode+"','"+comname+"','"+ck_rank+"','"+ck_score+"','"+fw_rank+"','"+fw_score+"',SYSDATE) ";
			ZhNodeMapper.importEexlDao(sql);
		}
	}

	private static String getValue(XSSFCell xssfCell){
		if(xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN){
			return String.valueOf( xssfCell.getBooleanCellValue());
		}else if(xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC){
			return String.valueOf( xssfCell.getNumericCellValue());
		}else{
			return String.valueOf( xssfCell.getStringCellValue());
		}
	}*/
}
