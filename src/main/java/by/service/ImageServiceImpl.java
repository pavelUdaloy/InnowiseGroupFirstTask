package by.service;

import by.dao.SessionFactory;
import by.entity.base.Image;
import by.entity.dto.ImageDto;
import by.exception.CustomFileToJsonException;
import by.mapper.ImageMapper;
import by.repository.ImageRepository;

import java.io.IOException;
import java.io.RandomAccessFile;

import static by.util.TextLabels.PROPERTIES_BASE_PATH;
import static by.util.TextLabels.READ;
import static by.util.TextLabels.property;

public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private final SessionFactory sessionFactory;

    public ImageServiceImpl(ImageRepository imageRepository, ImageMapper imageMapper, SessionFactory sessionFactory) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public byte[] get(Integer id) {
        Image image = imageRepository.get(id);
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
