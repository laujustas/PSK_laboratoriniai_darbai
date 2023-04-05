package mybatis.dao;

import java.util.List;
import mybatis.model.Plane;
import org.mybatis.cdi.Mapper;

@Mapper
public interface PlaneMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.plane
     *
     * @mbg.generated Mon Apr 03 14:52:15 EEST 2023
     */
    int deleteByPrimaryKey(Integer planeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.plane
     *
     * @mbg.generated Mon Apr 03 14:52:15 EEST 2023
     */
    int insert(Plane record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.plane
     *
     * @mbg.generated Mon Apr 03 14:52:15 EEST 2023
     */
    Plane selectByPrimaryKey(Integer planeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.plane
     *
     * @mbg.generated Mon Apr 03 14:52:15 EEST 2023
     */
    List<Plane> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.plane
     *
     * @mbg.generated Mon Apr 03 14:52:15 EEST 2023
     */
    int updateByPrimaryKey(Plane record);
}