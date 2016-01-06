/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.AlbumEntity;
import dao.PhotoEntity;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.Part;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author zakaridia
 */
public interface PhotoService{
    public PhotoEntity upload(CommonsMultipartFile file ,String username , AlbumEntity album)throws FileNotFoundException, IOException;
    public void add(PhotoEntity p);
    public void update(PhotoEntity photo);
    public boolean isValidExtension(Part part);
    public PhotoEntity find(Long id);
    public List<PhotoEntity> find(int limit);
    public List<PhotoEntity> findAll();
    public void delete(PhotoEntity photo);
}
