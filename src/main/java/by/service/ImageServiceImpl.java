package by.service;

import by.dao.EntityManagerProvider;
import by.entity.base.Image;
import by.entity.dto.ImageDto;
import by.exception.CustomFileToJsonException;
import by.exception.DaoOperationException;
import by.mapper.ImageMapper;
import by.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.RandomAccessFile;

import static by.util.TextLabels.PROPERTIES_BASE_PATH;
import static by.util.TextLabels.READ;
import static by.util.TextLabels.property;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public ImageServiceImpl(ImageRepository imageRepository, ImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    @Override
    public byte[] get(Integer id) {
        EntityManagerProvider.getEntityManager().getTransaction().begin();
        Image image;
        try {
            image = imageRepository.get(id);
            EntityManagerProvider.getEntityManager().getTransaction().commit();
        } catch (RuntimeException e) {
            EntityManagerProvider.getEntityManager().getTransaction().rollback();
            throw new DaoOperationException();
        } finally {
            EntityManagerProvider.clear();
        }
        ImageDto imageDto = imageMapper.convertImageToImageDto(image);
        try {
            return getImageBytes(imageDto);
        } catch (IOException e) {
            throw new CustomFileToJsonException();
        }
    }

    private byte[] getImageBytes(ImageDto imageDto) throws IOException {
        RandomAccessFile f = new RandomAccessFile(property.getProperty(PROPERTIES_BASE_PATH)
                + imageDto.getName() + imageDto.getFileFormat(), READ);
        byte[] b = new byte[(int) f.length()];
        f.readFully(b);
        return b;
    }
}
